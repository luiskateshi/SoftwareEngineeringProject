package com.example.uniratingwebapp.controllers;

import com.example.uniratingwebapp.DTOs.FeedbackDTO;
import com.example.uniratingwebapp.entities.Course;
import com.example.uniratingwebapp.entities.Feedback;
import com.example.uniratingwebapp.repositories.CourseRepository;
import com.example.uniratingwebapp.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/getByCourse/{courseId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByCourse(@PathVariable(name = "courseId") Long courseId) {
        List<Feedback> feedbacks = feedbackRepository.findAllByCourseId(courseId);

        if (feedbacks.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return a 404 Not Found response
        } else {
            List<FeedbackDTO> feedbackDTOs = convertToDTOs(feedbacks);
            return ResponseEntity.ok(feedbackDTOs);
        }
    }

    private List<FeedbackDTO> convertToDTOs(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Helper method to convert Feedback entity to FeedbackDTO
    private FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setStudentName(feedback.getStudent().getName());
        dto.setMessage(feedback.getFeedback());
        dto.setRating(feedback.getRating());
        return dto;
    }
}
