package com.example.uniratingwebapp.controllers;
import com.example.uniratingwebapp.entities.Course;
import com.example.uniratingwebapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public String home() {
        return "index";
    }


    @GetMapping(path="/getAll")
    public @ResponseBody List<Course> getCourses() {
        return courseRepository.findAll();
    }
}

