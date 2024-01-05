package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.entities.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    //Get all feedbacks for a course
    public List<Feedback> findAllByCourseId(Long courseId);
}
