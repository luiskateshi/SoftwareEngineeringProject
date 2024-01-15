package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.DTOs.CourseRatingDTO;
import com.example.uniratingwebapp.entities.Feedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    //Get all feedbacks for a course
    public List<Feedback> findAllByCourseId(Long courseId);

    //Get top 8 rated courses using a query

    @Query("SELECT new com.example.uniratingwebapp.DTOs.CourseRatingDTO(f.course, AVG(f.rating)) " +
            "FROM Feedback f " +
            "GROUP BY f.course " +
            "ORDER BY AVG(f.rating) DESC")

    public List<CourseRatingDTO> findTop8ByOrderByRatingDesc(Pageable pageable);

    Boolean existsByStudentIdAndCourseId(Long id, Long courseId);


    List<Feedback> findByCourseIdAndCreationDateBefore(Long courseId, LocalDateTime date);
}

