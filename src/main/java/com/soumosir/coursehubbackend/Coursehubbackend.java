package com.soumosir.coursehubbackend;

import com.soumosir.coursehubbackend.model.*;
import com.soumosir.coursehubbackend.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class Coursehubbackend {

	public static void main(String[] args) {
		SpringApplication.run(Coursehubbackend.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
//	@Bean
//	public LoginAttemptService loginAttemptService(){
//		return new LoginAttemptService();
//	};

	@Bean
	CommandLineRunner run(UserService userService, EmailService emailService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			AppUser appUser1  = userService.saveUser(new AppUser(null,"Soumosir Dutta","sdutta","sdutta7@umd.edu","Maryland@1234567",new ArrayList<>()));
			AppUser appUser2  = userService.saveUser(new AppUser(null,"John Doe","jdoe","soumosir@gmail.com","Maryland@1234567",new ArrayList<>()));
			AppUser appUser3  = userService.saveUser(new AppUser(null,"Burry Bacca","bbacca","bscscs@ycns.com","Maryland@1234567",new ArrayList<>()));
			AppUser appUser4  = userService.saveUser(new AppUser(null,"Vincent Yu","vin","soumosirdutta@gmail.com","Maryland@1234",new ArrayList<>()));

			userService.addRoleToUser("sdutta","ROLE_ADMIN");

//			emailService.sendEmail("ABC TEST","abc.com","123");
		};
	}

}
