package com.soumosir.coursehubbackend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.coursehubbackend.model.*;
import com.soumosir.coursehubbackend.model.helper.AppUserRest;
import com.soumosir.coursehubbackend.model.helper.CourseResponseRest;
import com.soumosir.coursehubbackend.service.CourseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<CourseResponseRest>> getCourses(){
        return ResponseEntity.ok().body(courseService.getCourses().stream().map(CourseResponseRest::new).collect(Collectors.toList()));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseResponseRest> getCourses(@PathVariable Long id){
        Course course  = courseService.getCourse(id);
        CourseResponseRest crr = new CourseResponseRest(course,true);
        return ResponseEntity.ok().body(crr);
    }

//    @GetMapping("/user/{username}")
//    public ResponseEntity<AppUserRest> getUser(@PathVariable String username) {
//        return ResponseEntity.ok().body(new AppUserRest(userService.getUser(username)));
//    }


    @PutMapping("/course/enrolluser")
    public ResponseEntity<?> enrollCourse(@RequestBody EnrollCourseForm form, Authentication authentication, HttpServletResponse response) throws IOException {
        try {
            courseService.enrollCourse(authentication.getPrincipal().toString(), form.courseId);

            return ResponseEntity.ok().build();
        }
        catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/course/unenrolluser")
    public ResponseEntity<?> unenrollCourse(@RequestBody EnrollCourseForm form, Authentication authentication, HttpServletResponse response) throws IOException {
        try {
            courseService.unenrollCourse(authentication.getPrincipal().toString(), form.courseId);

            return ResponseEntity.ok().build();
        }
        catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/course/addwishlist")
    public ResponseEntity<?> addWishlist(@RequestBody AddWishlistForm form,Authentication authentication,HttpServletResponse response) throws IOException {
        try {
            courseService.addWishlist(authentication.getPrincipal().toString(), form.wishlistId);
            return ResponseEntity.ok().build();
        }
        catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/course/removewishlist")
    public ResponseEntity<?> removeWishlist(@RequestBody AddWishlistForm form,Authentication authentication,HttpServletResponse response) throws IOException {
        try {
            courseService.removeWishlist(authentication.getPrincipal().toString(), form.wishlistId);
            return ResponseEntity.ok().build();
        }
        catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/course/enrolled")
    public ResponseEntity<List<CourseResponseRest>> getEnrolledCourses(Authentication authentication){
        return ResponseEntity.ok().body(courseService.getEnrolledCourses(authentication.getPrincipal().toString()).stream().map(CourseResponseRest::new).collect(Collectors.toList()));
    }

    @GetMapping("/course/wishlist")
    public ResponseEntity<List<CourseResponseRest>> getWishlistCourses(Authentication authentication){
        return ResponseEntity.ok().body(courseService.getWishlistCourses(authentication.getPrincipal().toString()).stream().map(CourseResponseRest::new).collect(Collectors.toList()));
    }

    @PostMapping("/course/submitexam")
    public ResponseEntity<?> submitExam(@RequestBody ExamForm form,Authentication authentication,HttpServletResponse response) throws IOException {
        try {
            ExamResult result = courseService.submitExam(authentication.getPrincipal().toString(),form.examId, form.answers);
            Map<String,Long> marks = new HashMap<>();
            marks.put("marks",result.getMarks());
            return ResponseEntity.ok().body(marks);
        }
        catch (Exception exception) {
            response.setHeader("error", exception.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        return ResponseEntity.badRequest().build();
    }



}

@Data
class ExamForm {
    public String answers;
    public Long examId;
}

@Data
class EnrollCourseForm {
    public Long courseId;
}

@Data
class AddWishlistForm {
    public Long wishlistId;
}

