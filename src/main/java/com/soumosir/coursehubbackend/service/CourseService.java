package com.soumosir.coursehubbackend.service;

import com.soumosir.coursehubbackend.model.*;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();
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
}

