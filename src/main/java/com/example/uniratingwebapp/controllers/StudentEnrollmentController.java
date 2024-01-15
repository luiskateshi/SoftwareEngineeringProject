package com.example.uniratingwebapp.controllers;

import com.example.uniratingwebapp.entities.StudentEnrollment;
import com.example.uniratingwebapp.repositories.CourseRepository;
import com.example.uniratingwebapp.repositories.StudentEnrollmentRepository;
import com.example.uniratingwebapp.repositories.StudentRepository;
import com.example.uniratingwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/studentEnrollments")
public class StudentEnrollmentController {
    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;


    @GetMapping("/isStudentEnrolledInCourse/{courseId}")
    public boolean isStudentEnrolledInCourse(@PathVariable(name = "courseId") Long courseId, Principal principal) {
        Long studentId = userService.findByUsername(principal.getName()).getId();
        return studentEnrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }

    @Transactional
    @DeleteMapping("/deleteStudentEnrollment/{courseId}")
    public ResponseEntity<Boolean> deleteStudentEnrollment(@PathVariable(name = "courseId") Long courseId, Principal principal) {
        Long studentId = userService.findByUsername(principal.getName()).getId();
        studentEnrollmentRepository.deleteStudentEnrollmentByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/saveStudentEnrollment/{courseId}")
    public ResponseEntity<Boolean> saveStudentEnrollment(@PathVariable(name = "courseId") Long courseId, Principal principal) {
        Long studentId = userService.findByUsername(principal.getName()).getId();
        var student = studentRepository.getById(studentId);
        var course = courseRepository.getById(courseId);
        studentEnrollmentRepository.save(new StudentEnrollment(student, course));
        return ResponseEntity.ok(true);
    }


}
