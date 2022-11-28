package com.soumosir.coursehubbackend.securityfilter;
import com.soumosir.coursehubbackend.cache.RateLimitingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomRateLimitingFilter extends OncePerRequestFilter {

    private final RateLimitingService rateLimitingService;

    public CustomRateLimitingFilter(RateLimitingService rateLimitingService){
        this.rateLimitingService = rateLimitingService;
    }



    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String ip = getClientIP(request);
        if (rateLimitingService.isRateLimited(ip)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
            return;
        }
        rateLimitingService.apiCalled(ip);
        filterChain.doFilter(request, response);
    }
}
