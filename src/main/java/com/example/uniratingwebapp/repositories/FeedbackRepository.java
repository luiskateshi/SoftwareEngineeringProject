package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.DTOs.CourseRatingDTO;
import com.example.uniratingwebapp.entities.Feedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
// public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    //Get all feedbacks for a course
    public List<Feedback> findAllByCourseId(Long courseId);

    //Get top 8 rated courses using a query


    @Query(value = "SELECT new com.example.uniratingwebapp.DTOs.CourseRatingDTO(f.course_id, AVG(f.rating)) FROM Feedback f GROUP BY f.course_id ORDER BY AVG(f.rating) DESC LIMIT 8", nativeQuery = true)
    public List<CourseRatingDTO> findTop8ByOrderByRatingDesc(Pageable pageable);


    List<Feedback> findByCourseIdAndCreationDateBefore(Long courseId, LocalDateTime date);



    Boolean existsByStudentIdAndCourseId(Long id, Long courseId);
}
