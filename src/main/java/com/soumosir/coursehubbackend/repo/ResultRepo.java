package com.soumosir.coursehubbackend.repo;

import com.soumosir.coursehubbackend.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<ExamResult, Long> {
}
