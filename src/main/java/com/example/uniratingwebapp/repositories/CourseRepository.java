package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.Title LIKE %?1%")
    public List<Course> findByTitle(String searchTerm);

    @Query("SELECT c FROM Course c JOIN StudentEnrollment se ON se.course.Id = c.Id WHERE se.student.id = :studentId")
    List<Course> findCoursesByStudentId(Long studentId);

}
