package com.example.uniratingwebapp.services;

import com.example.uniratingwebapp.entities.Feedback;
import com.example.uniratingwebapp.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void deleteOldFeedbacks(Long courseId) {
        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);
        List<Feedback> oldFeedbacks = feedbackRepository.findByCourseIdAndCreationDateBefore(courseId, oneMinuteAgo);
        feedbackRepository.deleteAll(oldFeedbacks);
    }
}
