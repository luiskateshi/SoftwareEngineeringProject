package com.example.uniratingwebapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;


    private int rating;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;



    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @PrePersist
    public void prePersist() {
        dateAdded = LocalDateTime.now(); // Set the current date and time
    }

    public Feedback( String feedback, int rating, Student student, Course course) {
        this.feedback = feedback;
        this.rating = rating;
        this.student = student;
        this.course = course;
    }
}