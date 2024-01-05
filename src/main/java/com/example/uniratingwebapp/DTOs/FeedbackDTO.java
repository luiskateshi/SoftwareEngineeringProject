package com.example.uniratingwebapp.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {
    private Long id;
    private String message;
    private String studentName;
    private int rating;

}
