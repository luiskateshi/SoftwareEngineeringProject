package com.example.uniratingwebapp.repositories;

import com.example.uniratingwebapp.entities.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    @Modifying
    @Query("DELETE FROM StudentEnrollment se WHERE se.student.id = :studentId AND se.course.Id = :courseId")
    void deleteStudentEnrollmentByStudentIdAndCourseId(Long studentId, Long courseId);

    @Modifying
    @Query("INSERT INTO StudentEnrollment se (se.student.Id, se.course.Id) VALUES (:studentId, :courseId)")
    StudentEnrollment saveStudentEnrollmentByStudentIdAndCourseId(Long studentId, Long courseId);

}
