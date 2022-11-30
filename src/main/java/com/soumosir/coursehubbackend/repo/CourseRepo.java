package com.soumosir.coursehubbackend.repo;

import com.soumosir.coursehubbackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}