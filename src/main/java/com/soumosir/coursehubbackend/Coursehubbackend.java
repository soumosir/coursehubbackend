package com.soumosir.coursehubbackend;

import com.soumosir.coursehubbackend.model.*;
import com.soumosir.coursehubbackend.service.CourseService;
import com.soumosir.coursehubbackend.service.EmailService;
import com.soumosir.coursehubbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class Coursehubbackend {

    public static void main(String[] args) {
        SpringApplication.run(Coursehubbackend.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

	@Bean
	CommandLineRunner run(UserService userService, EmailService emailService, CourseService courseService) {
		return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            AppUser appUser1 = userService.saveUser(new AppUser(null, "Soumosir Dutta", "sdutta", "sdutta7@umd.edu", "Maryland@1234567", new ArrayList<>()));
            AppUser appUser2 = userService.saveUser(new AppUser(null, "John Doe", "jdoe", "soumosir@gmail.com", "Maryland@1234567", new ArrayList<>()));
            AppUser appUser3 = userService.saveUser(new AppUser(null, "Burry Bacca", "bbacca", "bscscs@ycns.com", "Maryland@1234567", new ArrayList<>()));
            AppUser appUser4 = userService.saveUser(new AppUser(null, "Vincent Yu", "vin", "soumosirdutta@gmail.com", "Maryland@1234", new ArrayList<>()));

            userService.addRoleToUser("sdutta", "ROLE_ADMIN");

			Map<String,List<String>> questions = new HashMap<>();
			Map<String,String> answers = new HashMap<>();

			Exam exam1 = courseService.saveExam(new Exam(null,"Quiz name 1","QUIZ",120L,questions.toString(),answers.toString()));
			Exam exam2 = courseService.saveExam(new Exam(null,"Final exam name 1","EXAM",120L,questions.toString(),answers.toString()));

			Content content1 = courseService.saveContent(new Content(
					null, "lecture 1","video","google.com"
			));
			Content content2 = courseService.saveContent(new Content(
					null, "lecture pdf","image","google.com"
			));

			Course course1 = courseService.saveCourse(new Course(null,"Data and Algo","ENPM613","description 123",appUser1,
					Timestamp.valueOf(LocalDateTime.now()),
					Timestamp.valueOf(LocalDateTime.now()),
					60L,
					null,
					null,
					null));

			Course course2 = courseService.saveCourse(new Course(null,"Human computing","ENPM312","himans  123",appUser1,
					Timestamp.valueOf(LocalDateTime.now()),
					Timestamp.valueOf(LocalDateTime.now()),
					60L,
					null,
					null,
					null));

			courseService.addContent(content1.getId(),course1.getId());
			courseService.addContent(content2.getId(),course1.getId());


			courseService.addExam(exam1.getId(),course1.getId());
			courseService.addExam(exam2.getId(),course2.getId());




//			emailService.sendEmail("ABC TEST","abc.com","123");
        };
    }

}
