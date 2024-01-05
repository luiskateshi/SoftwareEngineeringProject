package com.example.uniratingwebapp;

import com.example.uniratingwebapp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public String showHome() {
        return "HomePage";
    }

    @GetMapping("/courses")
    public String showCourses() {
        return "HomePage";
    }

    @GetMapping("/myCourses")
    public String showMyCourses() {
        return "myCourses";
    }

    @GetMapping("/calendar")
    public String showCalendar() {
        return "calendar";
    }


    @GetMapping(path="/getCourse/{Id}")
    public String showCourseWithId( Model model, @PathVariable Long Id) {

        Id=0L;
        model.addAttribute("course", courseRepository.findById(Id));
        return "courseDetails";
    }


}
