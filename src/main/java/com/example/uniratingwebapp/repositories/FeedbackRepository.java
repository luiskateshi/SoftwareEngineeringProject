package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.DTOs.CourseRatingDTO;
import com.example.uniratingwebapp.entities.Feedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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


    //stored procedure that will be run
    /*
    DELIMITER //
    DROP PROCEDURE IF EXISTS DeleteOldFeedbacks;
    CREATE PROCEDURE DeleteOldFeedbacks()
    BEGIN
        DECLARE cutoff_date DATE;

        -- Calculate the cutoff date (1 year ago from the current date)
        SET cutoff_date = DATE_SUB(CURDATE(), INTERVAL 1 YEAR);

        -- Delete feedbacks older than the cutoff date
        DELETE FROM feedback WHERE date_added < cutoff_date
        ORDER BY id -- Replace 'your_key_column' with your actual key column
        LIMIT 1000;
    END //

    DELIMITER ;
     */
    @Modifying
    @Procedure(name = "DeleteOldFeedbacks")
    void deleteOldFeedbacks();




}

