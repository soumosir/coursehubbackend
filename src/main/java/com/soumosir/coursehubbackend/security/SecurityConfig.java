package com.soumosir.coursehubbackend.security;

import com.soumosir.coursehubbackend.cache.RateLimitingService;
import com.soumosir.coursehubbackend.securityfilter.CustomAuthenticationFilter;
import com.soumosir.coursehubbackend.securityfilter.CustomAuthorizationFilter;
import com.soumosir.coursehubbackend.cache.LoginAttemptService;
import com.soumosir.coursehubbackend.securityfilter.CustomRateLimitingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    provided by spring
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private RateLimitingService rateLimitingService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        we dont use session , we use jwt
//        super.configure(http);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(),loginAttemptService);
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        allow everyone to access this application with a token
        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**","/api/resetpassword/**","/api/user/register/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/user/{username}/**").access("@userSecurity.hasUsername(authentication,#username)");
        http.authorizeRequests().antMatchers(PUT, "/api/user/{username}/**").access("@userSecurity.hasUsername(authentication,#username)");
        http.authorizeRequests().antMatchers(DELETE, "/api/user/{username}/**").access("@userSecurity.hasUsername(authentication,#username)");


        http.authorizeRequests().antMatchers(POST, "/api/users/**").hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().antMatchers(POST, "/role/addtouser/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/role/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/role/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/api/role/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/api/role/**").hasAnyAuthority("ROLE_ADMIN");


        http.authorizeRequests().antMatchers(GET, "/api/course/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/api/course/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
        http.authorizeRequests().antMatchers(PUT, "/api/course/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
        http.authorizeRequests().antMatchers(DELETE, "/api/course/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");

        http.authorizeRequests().antMatchers(GET, "/api/exam/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER","ROLE_INSTRUCTOR");
        http.authorizeRequests().antMatchers(POST, "/api/exam/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
        http.authorizeRequests().antMatchers(PUT, "/api/exam/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");
        http.authorizeRequests().antMatchers(DELETE, "/api/exam/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER");

        http.authorizeRequests().antMatchers(GET, "/api/courseresult/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER","ROLE_INSTRUCTOR");


        http.authorizeRequests().anyRequest().authenticated();
//        we add a custom filter
        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new CustomRateLimitingFilter(rateLimitingService),UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
