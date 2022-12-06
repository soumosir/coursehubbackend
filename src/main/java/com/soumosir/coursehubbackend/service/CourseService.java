package com.soumosir.coursehubbackend.service;

import com.soumosir.coursehubbackend.model.*;
import org.json.JSONException;

import java.util.Arrays;
import java.util.List;

public interface CourseService {

    List<Course> getCourses();
    List<Course> getCourses(String instructorName);
    Course saveCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long id);

    Exam saveExam(Exam exam);
    Exam updateExam(Exam exam);
    void deleteExam(Long id);

    Content saveContent(Content content);
    Content updateContent(Content content);
    void deleteContent(Long id);

    void addContent(Long contentId,Long courseId);
    void addExam(Long examId,Long courseId);


    void enrollCourse(String username, Long courseId) throws Exception;
    void addWishlist(String username, Long courseId);

    List<Course> getEnrolledCourses(String toString);

    List<Course> getWishlistCourses(String toString);

    ExamResult submitExam(String username,Long examId, String answers) throws JSONException;

    void unenrollCourse(String toString, Long courseId);

    void removeWishlist(String toString, Long wishlistId);

    Course getCourse(Long id);

    Exam getExam(Long id,String username);

    List<Exam> getExams(String username);

    List<ExamResult> getResultByCourseId(Long courseId, String username);

    Content getContent(Long id,String username);
}

