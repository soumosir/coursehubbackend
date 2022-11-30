package com.soumosir.coursehubbackend.repo;

import com.soumosir.coursehubbackend.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepo extends JpaRepository<Content, Long> {
}
