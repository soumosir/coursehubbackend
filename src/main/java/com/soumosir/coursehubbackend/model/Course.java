package com.soumosir.coursehubbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Slf4j
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotEmpty
    @NotNull
    private String name;
    @Column(unique=true)
    @NotEmpty
    @NotNull
    @Unique
    private String code;
    @NotEmpty
    @NotNull
    private String description;

    private String instructor;


    private Timestamp startTime;

    private Timestamp endTime;

    @NotEmpty
    @NotNull
    private Long totalSeats;

    private Long remainingSeats;

    @OneToMany(fetch = LAZY)
    private Collection<Content> contents;

    @OneToMany(fetch = LAZY)
    private Collection<Exam> exams;


    @ManyToMany(fetch = LAZY)
    private Collection<AppUser> wishlistUsers = new ArrayList<>();

    @ManyToMany(fetch = LAZY)
    private Collection<AppUser> enrolledUsers = new ArrayList<>();

    public void validate() throws ValidationException {



        if(name==null || (name!=null && (name.length()<3|| name.length()>2000))){
            log.error("name is less than 3 letters or more than 20 : "+ name );
            throw new ValidationException("name is less than 3 letters or more than 20 : "+ name );
        }


        if(!code.matches("^[a-zA-Z0-9]+$")){
            log.error("code is should be alphanumeric A-Z , a-z or 0-9 : "+ code );
            throw new ValidationException("Username is should be alphanumeric A-Z , a-z or 0-9 : "+code);
        }

        if(code==null ||(code!=null && (code.length()<3 || code.length()>3000))){
            log.error("code is not valid should be more than 3 and les than 30 : "+ code );
            throw new ValidationException("code is not valid should be more than 3 and les than 30 : "+code);
        }



    }
}
