package com.soumosir.coursehubbackend.repo;

import com.soumosir.coursehubbackend.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepo extends JpaRepository<Exam, Long> {
    List<Exam> findByUser(String username);
}