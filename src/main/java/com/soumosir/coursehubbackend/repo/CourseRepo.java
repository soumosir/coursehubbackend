package com.soumosir.coursehubbackend.repo;

import com.soumosir.coursehubbackend.model.AppUser;
import com.soumosir.coursehubbackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByEnrolledUsers(AppUser appUser);
    List<Course> findByWishlistUsers(AppUser appUser);
}