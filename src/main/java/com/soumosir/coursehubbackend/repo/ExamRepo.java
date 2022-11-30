package com.soumosir.coursehubbackend.repo;

import com.soumosir.coursehubbackend.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepo extends JpaRepository<Exam, Long> {
}