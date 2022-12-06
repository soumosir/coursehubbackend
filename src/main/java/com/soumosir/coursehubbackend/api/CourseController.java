package com.soumosir.coursehubbackend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.coursehubbackend.model.*;
import com.soumosir.coursehubbackend.model.helper.AppUserRest;
import com.soumosir.coursehubbackend.model.helper.CourseRequestRest;
import com.soumosir.coursehubbackend.model.helper.CourseResponseRest;
import com.soumosir.coursehubbackend.service.CourseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {


    private final CourseService courseService;

    @PostMapping("/exam")
    public ResponseEntity<Exam> saveExam(@RequestBody Exam exam,Authentication authentication){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/exam").toUriString());
        exam.setUser(authentication.getPrincipal().toString());
        return ResponseEntity.created(uri).body(courseService.saveExam(exam));
    }

    @GetMapping("/exam")
    public ResponseEntity<List<Exam>> getExams(Authentication authentication){
        List<Exam> exams  = courseService.getExams(authentication.getPrincipal().toString());
        return ResponseEntity.ok().body(exams);
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Long id,Authentication authentication,HttpServletResponse response) throws IOException {
        try {
            Exam exam = courseService.getExam(id, authentication.getPrincipal().toString());

            exam.setAnswers(null);
            return ResponseEntity.ok().body(exam);
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

    @GetMapping("/content/{id}")
    public ResponseEntity<Content> getContent(@PathVariable Long id,Authentication authentication,HttpServletResponse response) throws IOException {
        try {
            Content content = courseService.getContent(id, authentication.getPrincipal().toString());
            return ResponseEntity.ok().body(content);
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

    @PostMapping("/content")
    public ResponseEntity<Content> postContent(@RequestBody Content content,Authentication authentication){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/content").toUriString());
        content.setUsername(authentication.getPrincipal().toString());
        return ResponseEntity.created(uri).body(courseService.saveContent(content));
    }

    @PostMapping("/course")
    public ResponseEntity<Course> postCourse(@RequestBody Course course, Authentication authentication){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/course").toUriString());
        if(course.getInstructor()!=null && !Objects.equals(course.getInstructor(), authentication.getPrincipal().toString())){
            return ResponseEntity.badRequest().build();
        }
        Collection<Content> savedContent = new ArrayList<>();
        course.getContents().forEach(content -> {
            content.setUsername(authentication.getPrincipal().toString());
            savedContent.add(courseService.saveContent(content));
        });
        course.setContents(savedContent);
        Collection<Exam> savedExam = new ArrayList<>();
        course.getExams().forEach(exam -> {
            exam.setUser(authentication.getPrincipal().toString());
            savedExam.add(courseService.saveExam(exam));
        });
        course.setExams(savedExam);
        course.setInstructor(authentication.getPrincipal().toString());
        return ResponseEntity.created(uri).body(courseService.saveCourse(course));

    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseResponseRest>> getCourses(){
        return ResponseEntity.ok().body(courseService.getCourses().stream().map(CourseResponseRest::new).collect(Collectors.toList()));
    }

    @GetMapping("/createdcourses")
    public ResponseEntity<List<CourseResponseRest>> getCreatedCourses(Authentication authentication){
        return ResponseEntity.ok().body(courseService.getCourses(authentication.getPrincipal().toString()).stream().map(CourseResponseRest::new).collect(Collectors.toList()));
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

    @PostMapping("/exam/submit")
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

    @GetMapping("/courseresult/{id}")
    public ResponseEntity<List<ExamResult>> getResultByCourseId(@PathVariable Long id,Authentication authentication){

        List<ExamResult> results = courseService.getResultByCourseId(id,authentication.getPrincipal().toString());
        return ResponseEntity.ok().body(results);
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

