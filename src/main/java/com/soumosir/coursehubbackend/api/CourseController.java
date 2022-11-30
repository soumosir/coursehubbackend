package com.soumosir.coursehubbackend.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.coursehubbackend.cache.ForgotPasswordService;
import com.soumosir.coursehubbackend.model.*;
import com.soumosir.coursehubbackend.model.helper.AppUserRest;
import com.soumosir.coursehubbackend.model.helper.ForgetPasswordAppUser;
import com.soumosir.coursehubbackend.service.CourseService;
import com.soumosir.coursehubbackend.service.EmailService;
import com.soumosir.coursehubbackend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {


    private final CourseService courseService;

    @PostMapping("/exam")
    public ResponseEntity<Exam> saveUser(@RequestBody Exam exam){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/exam").toUriString());
        return ResponseEntity.created(uri).body(courseService.saveExam(exam));
    }

    @PostMapping("/content")
    public ResponseEntity<Content> saveUser(@RequestBody Content content){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/content").toUriString());
        return ResponseEntity.created(uri).body(courseService.saveContent(content));
    }

    @PostMapping("/course")
    public ResponseEntity<Course> saveUser(@RequestBody Course course){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/course").toUriString());
        return ResponseEntity.created(uri).body(courseService.saveCourse(course));
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getCourses(){
        return ResponseEntity.ok().body(courseService.getCourses());
    }

}


