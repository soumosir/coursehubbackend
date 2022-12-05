package com.soumosir.coursehubbackend.repo;

import com.soumosir.coursehubbackend.model.AppUser;
import com.soumosir.coursehubbackend.model.Exam;
import com.soumosir.coursehubbackend.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ResultRepo extends JpaRepository<ExamResult, Long> {
    ExamResult findByExamAndUser(Exam exam, AppUser user);
    Collection<ExamResult> findByUserAndExamIsIn(AppUser user, Collection<Exam> exam);

}
