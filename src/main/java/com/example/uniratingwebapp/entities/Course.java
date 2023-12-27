package com.example.uniratingwebapp.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Long Id;

    @Column(name="title")
    private String Title;

    @Column(name="description", nullable = true)
    private String Description;

    @Column(name="lecturer", nullable = true)
    private String Lecturer;

    @Column(name="startDate", nullable = true)
    private String startDate;

    @Column(name="maxNumberOfStudents", nullable = true)
    private int maxNumberOfStudents;

    @Column(name="currentNumberOfStudents", nullable = true)
    private int currentNumberOfStudents;

    @Column(name="image", nullable = true)
    private String image;
}
