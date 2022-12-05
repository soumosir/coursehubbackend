package com.soumosir.coursehubbackend.service;

import com.google.gson.Gson;
import com.soumosir.coursehubbackend.exceptions.ResourceNotFoundException;
import com.soumosir.coursehubbackend.model.*;
import com.soumosir.coursehubbackend.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImplementation implements CourseService{

    private final CourseRepo courseRepo;
    private final ExamRepo examRepo;
    private final ContentRepo contentRepo;
    private final AppUserRepo appUserRepo;
    private final ResultRepo resultRepo;


    @Override
    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course saveCourse(Course course){
        course.validate();
        courseRepo.save(course);
        return course;
    }

    @Override
    public Course updateCourse(Course course){
        course.validate();
//        courseRepo.save(course);
        return course;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }



    @Override
    public Content saveContent(Content content) {
        content.validate();
        contentRepo.save(content);
        return content;
    }

    @Override
    public Content updateContent(Content content) {
        content.validate();
//        contentRepo.save(content);
        return content;
    }

    @Override
    public void deleteContent(Long id) {
        contentRepo.deleteById(id);
    }

    @Override
    public void addContent(Long contentId, Long courseId){
        Course course = courseRepo.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Course id {} not present!",courseId))
        );
        Content content  = contentRepo.findById(contentId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Content id {} not present!",contentId))
        );
        Collection<Content> contents = course.getContents();
        contents.add(content);
        course.setContents(contents);
    }

    @Override
    public void addExam(Long examId, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Course id %s not present!",courseId))
        );
        Exam exam  = examRepo.findById(examId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("exam id %s not present!",examId))
        );
        Collection<Exam> exams = course.getExams();
        exams.add(exam);
        course.setExams(exams);
    }


    @Override @Transactional
    public void enrollCourse(String username, Long courseId) throws Exception {

        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course does not exist with id: " + courseId));;

        if(course.getEnrolledUsers().contains(appUser)){
            new ResourceNotFoundException("Course already enrolled ");
        }

        if(course.getEnrolledUsers().size()<=course.getTotalSeats()) {
            Long seats = course.getRemainingSeats();
            if(seats<=0){
                throw new Exception("Unable to enroll "+appUser.getUsername()+". Not available seats.");
            }

            log.info("Enrol to course to user  -> " + username + " -> " + courseId);
            seats-=1L;
            course.setRemainingSeats(seats);
            course.getEnrolledUsers().add(appUser);
        }
        else{
            throw new Exception("User cannot enroll . No seats allocted ");
        }
    }

    @Override
    public void addWishlist(String username, Long courseId){

        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course does not exist with id: " + courseId));;
        log.info("Add to wishlist course to user  -> " + username + " -> " + courseId);
        if(course.getWishlistUsers().contains(appUser)){
            new ResourceNotFoundException("Wishlist already added ");
        }
        course.getWishlistUsers().add(appUser);
    }

    @Override
    public List<Course> getEnrolledCourses(String username) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        return courseRepo.findByEnrolledUsers(appUser);
    }

    @Override
    public List<Course> getWishlistCourses(String username) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        return courseRepo.findByWishlistUsers(appUser);
    }

    private HashMap<String, String> jsonToMap(String t) throws JSONException {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(t);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key);
            map.put(key, value);

        }

        return map;
    }



    @Override @Transactional
    public void unenrollCourse(String username, Long courseId) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course does not exist with id: " + courseId));;

        if(!course.getEnrolledUsers().contains(appUser)){
            new ResourceNotFoundException("Course not  enrolled ");
        }
        Long seats = course.getRemainingSeats();
        course.setRemainingSeats(seats+1L);
        course.getEnrolledUsers().remove(appUser);

    }

    @Override
    public void removeWishlist(String username, Long courseId) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course does not exist with id: " + courseId));;
        log.info("Add to wishlist course to user  -> " + username + " -> " + courseId);
        if(!course.getWishlistUsers().contains(appUser)){
            new ResourceNotFoundException("Wishlist not added ");
        }
        course.getWishlistUsers().remove(appUser);
    }

    @Override
    public Course getCourse(Long id) {
        Course course =  courseRepo.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("course not found");
        });
        return course;
    }

    @Override
    public List<ExamResult> getResultByCourseId(Long courseId, String username) {
        Course course = courseRepo.findById(courseId).orElseThrow(()->{
            throw new ResourceNotFoundException("course not found");
        });
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));


        Collection<Exam> exams  = course.getExams();
        Collection<ExamResult> examResults =  resultRepo.findByUserAndExamIsIn(appUser,exams);
        return (List<ExamResult>)examResults;

    }

    @Override
    public Content getContent(Long id,String username) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Content content  = contentRepo.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Content not found");
        });
        List<Course> courses = courseRepo.findByContents(content);
        if(courses.size()==0){
            throw new ResourceNotFoundException(String.format("Content id %s not present in any course!",id));
        }
        if(!courses.get(0).getEnrolledUsers().contains(appUser)){
            throw new ResourceNotFoundException(String.format("User %s is not enrolled in the course %s hence cannot view the content!",appUser.getUsername(),courses.get(0).getName()));
        }
        return content;
    }

    @Override
    public Exam getExam(Long id,String username) {
        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Exam exam  = examRepo.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Exam not found");
        });
        List<Course> courses = courseRepo.findByExams(exam);
        if(courses.size()==0){
            throw new ResourceNotFoundException(String.format("Exam id %s not present in any course!",id));
        }
        if(!courses.get(0).getEnrolledUsers().contains(appUser)){
            throw new ResourceNotFoundException(String.format("User %s is not enrolled in the course %s hence cannot view the exam!",appUser.getUsername(),courses.get(0).getName()));
        }
        return exam;
    }

    @Override
    public List<Exam> getExams(String username){
        List<Exam> exams = examRepo.findByUser(username);
        return exams;
    }


    @Override
    public ExamResult submitExam(String username,Long examId, String userAnswers) throws JSONException {

        AppUser appUser = appUserRepo.findByUsername(username).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("user does not exist with username: " + username));
        Exam exam  = examRepo.findById(examId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("exam id {} not present!",examId))
        );
        List<Course> courses = courseRepo.findByExams(exam);
        if(courses.size()==0){
            throw new ResourceNotFoundException(String.format("Exam id {} not present in any course!",examId));
        }
        if(!courses.get(0).getEnrolledUsers().contains(appUser)){
            throw new ResourceNotFoundException(String.format("User {} is not enrolled in the course {} hence cannot take the exam!",appUser.getUsername(),courses.get(0).getName()));
        }

        ExamResult examResult1 = resultRepo.findByExamAndUser(exam,appUser);
        if(examResult1!=null && examResult1.getMarks()!=-1){
            throw new ResourceNotFoundException("You have already given the exam and your score was "+examResult1.getMarks()+ "%");
        }

        String correctAnswers = exam.getAnswers();

        HashMap<String,String> userAnswersMap = jsonToMap(userAnswers);
        HashMap<String,String> correctAnswersMap = jsonToMap(correctAnswers);
        Long marks = 0L;
        for (var entry : correctAnswersMap.entrySet()) {

            if (userAnswersMap.containsKey(entry.getKey())){
                var ans = userAnswersMap.get(entry.getKey());
                if(ans.equals(entry.getValue())){
                    marks+=1;
                }
            }
        }
        double m = (double)(marks) / (double)(correctAnswersMap.entrySet().size());
        marks = (long)(m*100L);

        ExamResult examResult = new ExamResult(null,marks,userAnswers,appUser,exam);
        return resultRepo.save(examResult);
    }

    @Override
    public Exam saveExam(Exam exam) {
        exam.validate();
        examRepo.save(exam);
        return exam;

    }

    @Override
    public Exam updateExam(Exam exam) {
        exam.validate();
//        examRepo.save(exam);
        return exam;
    }

    @Override
    public void deleteExam(Long id) {
        examRepo.deleteById(id);
    }
}
