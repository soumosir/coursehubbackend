package com.soumosir.coursehubbackend.service;

import com.soumosir.coursehubbackend.exceptions.ResourceNotFoundException;
import com.soumosir.coursehubbackend.model.Content;
import com.soumosir.coursehubbackend.model.Course;
import com.soumosir.coursehubbackend.model.Exam;
import com.soumosir.coursehubbackend.repo.ContentRepo;
import com.soumosir.coursehubbackend.repo.CourseRepo;
import com.soumosir.coursehubbackend.repo.ExamRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImplementation implements CourseService{

    private final CourseRepo courseRepo;
    private final ExamRepo examRepo;
    private final ContentRepo contentRepo;


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
                new ResourceNotFoundException(String.format("Course id {} not present!",courseId))
        );
        Exam exam  = examRepo.findById(examId).orElseThrow(() ->
                new ResourceNotFoundException(String.format("exam id {} not present!",examId))
        );
        Collection<Exam> exams = course.getExams();
        exams.add(exam);
        course.setExams(exams);
    }
}
