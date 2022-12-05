package com.soumosir.coursehubbackend.model.helper;

import com.soumosir.coursehubbackend.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;


@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseRest {

    private Long id;
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String code;
    @NotEmpty
    @NotNull
    private String description;
    @NotEmpty
    @NotNull
    private String instructor;

    @NotEmpty
    @NotNull
    private Timestamp startTime;
    @NotEmpty
    @NotNull
    private Timestamp endTime;
    @NotEmpty
    @NotNull
    private Long totalSeats;
    private Long remainingSeats;

    private Collection<Content> contents;
    private Collection<Exam> exams;

    public CourseResponseRest(Course course){
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.description = course.getDescription();
        this.startTime = course.getStartTime();
        this.endTime = course.getEndTime();
        this.totalSeats = course.getTotalSeats();
        this.remainingSeats = course.getRemainingSeats();
        this.instructor = course.getInstructor();

    }

    public CourseResponseRest(Course course,Boolean detail){
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.description = course.getDescription();
        this.startTime = course.getStartTime();
        this.endTime = course.getEndTime();
        this.totalSeats = course.getTotalSeats();
        this.remainingSeats = course.getRemainingSeats();
        this.instructor = course.getInstructor();
        this.contents = course.getContents();
        Collection<Exam> exams = course.getExams();
        exams.forEach(exam -> {
            exam.setAnswers(null);
        });
        this.exams = exams;

    }



    public void validate() throws ValidationException {



        if(name==null || (name!=null && (name.length()<3|| name.length()>20))){
            log.error("name is less than 3 letters or more than 20 : "+ name );
            throw new ValidationException("name is less than 3 letters or more than 20 : "+ name );
        }


        if(!code.matches("^[a-zA-Z0-9]+$")){
            log.error("code is should be alphanumeric A-Z , a-z or 0-9 : "+ code );
            throw new ValidationException("Username is should be alphanumeric A-Z , a-z or 0-9 : "+code);
        }

        if(code==null ||(code!=null && (code.length()<3 || code.length()>30))){
            log.error("code is not valid should be more than 3 and les than 30 : "+ code );
            throw new ValidationException("code is not valid should be more than 3 and les than 30 : "+code);
        }



    }
}

