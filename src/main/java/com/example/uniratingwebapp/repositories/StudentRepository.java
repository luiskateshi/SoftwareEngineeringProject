package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
