package com.soumosir.coursehubbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Slf4j
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class ExamResult {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    private long marks;

    @OneToOne(fetch = EAGER)
    private AppUser user;

    @OneToOne(fetch = EAGER)
    private Exam exam;

    public void validate() throws ValidationException {


    }
}


