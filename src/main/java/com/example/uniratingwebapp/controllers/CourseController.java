package com.example.uniratingwebapp.controllers;
import org.springframework.http.ResponseEntity;
import com.example.uniratingwebapp.entities.Course;
import com.example.uniratingwebapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourseByTitle(@RequestParam(name = "search") String searchTerm) {
        List<Course> foundCourses = courseRepository.findByTitle(searchTerm);

        if (foundCourses.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return a 404 Not Found response
        } else {
            return ResponseEntity.ok(foundCourses); // Return the found courses
        }
    }

    @GetMapping(path="/get/{id}")
    public Optional<Course> getCourseById(@PathVariable(value = "id") Long courseId) {
        return courseRepository.findById(courseId);
    }

    @GetMapping(path="/getByStudent/{id}")
    public List<Course> getStudentCourses(@PathVariable(value = "id") Long studentId) {
        return courseRepository.findCoursesByStudentId(studentId);
    }



    @GetMapping(path="/getAll")
    public @ResponseBody List<Course> getCourses() {
        return courseRepository.findAll();
    }
}