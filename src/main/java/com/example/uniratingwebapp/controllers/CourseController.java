package com.example.uniratingwebapp.controllers;
import com.example.uniratingwebapp.DTOs.CourseRatingDTO;
import com.example.uniratingwebapp.entities.Student;
import com.example.uniratingwebapp.repositories.FeedbackRepository;
import com.example.uniratingwebapp.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.example.uniratingwebapp.entities.Course;
import com.example.uniratingwebapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourseByTitle(@RequestParam(name = "search") String searchTerm) {
        List<Course> foundCourses = courseRepository.findByTitle(searchTerm);

        if (foundCourses.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return a 404 Not Found response
        } else {
            return ResponseEntity.ok(foundCourses); // Return the found courses
        }
    }

    @GetMapping(path = "/get/{id}")
    public Optional<Course> getCourseById(@PathVariable(value = "id") Long courseId) {
        return courseRepository.findById(courseId);
    }

    @GetMapping(path = "/getByStudent")
    public List<Course> getStudentCourses(Principal principal) {
        Long studentId = userService.findByUsername(principal.getName()).getId();
        return courseRepository.findCoursesByStudentId(studentId);
    }


    @GetMapping(path = "/getAll")
    public @ResponseBody List<Course> getCourses() {
        return courseRepository.findAll();
    }


    @GetMapping(path = "/getTop8")
    public @ResponseBody List<CourseRatingDTO> getTop8Courses() {
        Pageable pageable = PageRequest.of(0, 8);
        List<CourseRatingDTO> top8Courses = feedbackRepository.findTop8ByOrderByRatingDesc(pageable);
        return top8Courses;
    }


}

