package com.example.uniratingwebapp.DTOs;

import com.example.uniratingwebapp.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
    private Student user;
    private String token;
    private boolean success;
}

