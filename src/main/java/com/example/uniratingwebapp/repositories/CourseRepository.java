package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
