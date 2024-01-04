package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.Title LIKE %?1%")
    public List<Course> findByTitle(String searchTerm);
}
