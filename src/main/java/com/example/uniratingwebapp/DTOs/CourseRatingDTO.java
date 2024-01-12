package com.example.uniratingwebapp.DTOs;

import com.example.uniratingwebapp.entities.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRatingDTO {

    private Course course;
    private double rating;

    public CourseRatingDTO(Course course, double rating) {
        this.course = course;
        this.rating = rating;
    }
}

