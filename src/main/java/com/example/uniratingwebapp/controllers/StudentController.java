package com.example.uniratingwebapp.controllers;

import com.example.uniratingwebapp.entities.Student;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.uniratingwebapp.repositories.StudentRepository;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

        @RequestMapping("/getAll")
        public List<Student> getAllStudents() {
            return studentRepository.findAll();
        }
}
