package com.example.uniratingwebapp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name="title")
    private String Title;

    @Column(name="description", nullable = true, length = 1000)
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
