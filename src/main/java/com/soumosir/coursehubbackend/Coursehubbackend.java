package com.soumosir.coursehubbackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
			userService.addRoleToUser("jdoe", "ROLE_INSTRUCTOR");

			Map<String,List<String>> questions = new HashMap<>();
			questions.put("what is 1+1?",List.of("3","2","1","0"));
			questions.put("what is array?",List.of("DS","wall","io","boolean"));
			questions.put("how many bytes is  char?",List.of("3","2","1","0"));
			questions.put("what is 1+9?",List.of("3","2","1","10"));

			Map<String,String> answers = new HashMap<>();
			answers.put("what is 1+1?","2");
			answers.put("what is array?","DS");
			answers.put("how many bytes is  char?","1");
			answers.put("what is 1+9?","10");

			String answersS="{}";
			String questionsS="{}";

			ObjectMapper objectMapper = new ObjectMapper();
			try {
				answersS = objectMapper.writeValueAsString(answers);
				questionsS = objectMapper.writeValueAsString(questions);
				System.out.println(answersS);
				System.out.println(questionsS);

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			Exam exam1 = courseService.saveExam(new Exam(null,"Hash map implementation","QUIZ",120L,questionsS,answersS,appUser2.getUsername()));
			Exam exam2 = courseService.saveExam(new Exam(null,"B Trees implementation Test","EXAM",120L,questionsS,answersS,appUser2.getUsername()));
//			Exam exam3 = courseService.saveExam(new Exam(null,"Final Assessment","EXAM",120L,questionsS,answersS));

			Content content1 = courseService.saveContent(new Content(
					null, "Sorting Algorithms Review","video","google.com",appUser2.getUsername(),"This content has a description"
			));
			Content content2 = courseService.saveContent(new Content(
					null, "Sorting Algorithms Deep Dive","image","google.com",appUser2.getUsername(),"A data structure is a named location that can be used to store and organize data."
			));


			Course course1 = courseService.saveCourse(new Course(null,"Data Structures and Algorithm","ENPM809W","A data structure is a named location that can be used to store and organize data. ",appUser2.getUsername(),
					Timestamp.valueOf(LocalDateTime.now()),
					Timestamp.valueOf(LocalDateTime.now()),
					5L,
					4L,
					null,
					null,
					null,
					List.of(appUser2)));

			Course course2 = courseService.saveCourse(new Course(null,"Human Computer Interaction","ENPM312","Human-computer interaction (HCI) is a multidisciplinary field of study focusing on the design of computer technology.",appUser2.getUsername(),
					Timestamp.valueOf(LocalDateTime.now()),
					Timestamp.valueOf(LocalDateTime.now()),
					20L,
					19L,
					null,
					null,
					List.of(appUser2),
					List.of(appUser3)));

			Course course3 = courseService.saveCourse(new Course(null,"Software Design and Implementation","ENPM613","The implementation stage of software development is the process of developing an executable system for delivery to the customer. Sometimes this involves separate activities of software design and programming. ." ,appUser2.getUsername(),
					Timestamp.valueOf(LocalDateTime.now()),
					Timestamp.valueOf(LocalDateTime.now()),
					60L,
					59L,
					null,
					null,List.of(appUser4),List.of(appUser3)));

			Course course4 = courseService.saveCourse(new Course(null,"Object Oriented Programming","ENPM083","The object oriented stage of software development is the process of developing an executable system for delivery to the customer. s." ,appUser2.getUsername(),
					Timestamp.valueOf(LocalDateTime.now()),
					Timestamp.valueOf(LocalDateTime.now()),
					60L,
					59L,
					null,
					null,List.of(appUser4),List.of(appUser3)));

			courseService.addContent(content1.getId(),course1.getId());
			courseService.addContent(content2.getId(),course1.getId());
//			courseService.addContent(content3.getId(),course1.getId());
//			courseService.addContent(content4.getId(),course1.getId());
//			courseService.addContent(content5.getId(),course1.getId());
//			courseService.addContent(content6.getId(),course1.getId());
//			courseService.addContent(content7.getId(),course1.getId());
//			courseService.addContent(content8.getId(),course1.getId());
//			courseService.addContent(content9.getId(),course1.getId());




			courseService.addExam(exam1.getId(),course1.getId());
			courseService.addExam(exam2.getId(),course2.getId());
//			courseService.addExam(exam3.getId(),course2.getId());




//			emailService.sendEmail("ABC TEST","abc.com","123");
        };
    }

}
