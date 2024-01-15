package com.example.uniratingwebapp.controllers;

import com.example.uniratingwebapp.DTOs.FeedbackDTO;
import com.example.uniratingwebapp.DTOs.PostFeedbackDTO;
import com.example.uniratingwebapp.entities.Course;
import com.example.uniratingwebapp.entities.Feedback;
import com.example.uniratingwebapp.entities.Student;
import com.example.uniratingwebapp.repositories.CourseRepository;
import com.example.uniratingwebapp.repositories.FeedbackRepository;
import com.example.uniratingwebapp.repositories.StudentRepository;
import com.example.uniratingwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/hasStudentLeftFeedbackOnCourse/{courseId}")
    public Boolean hasStudentLeftFeedbackOnCourse(@PathVariable(name = "courseId") Long courseId, Principal principal) {
        Long studentId = userService.findByUsername(principal.getName()).getId();
        return feedbackRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }

    @PostMapping("/addFeedback/{courseId}")
    public ResponseEntity<String> addFeedback(@RequestBody PostFeedbackDTO postFeedbackDTO, @PathVariable(name = "courseId") Long courseId , Principal principal) {
        Student student = userService.findByUsername(principal.getName());
        Course course = courseRepository.findById(courseId).get();
        var feedback = new Feedback( postFeedbackDTO.getMessage(), postFeedbackDTO.getRating(), student, course);
        feedbackRepository.save(feedback);
        return new ResponseEntity<>("Feedback added successfully", HttpStatus.CREATED);
    }

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
