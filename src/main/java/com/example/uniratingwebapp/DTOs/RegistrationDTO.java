package com.example.uniratingwebapp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    private String username;
    private String password;
    private String name;
    private String email;

    @Override
    public String toString() {
        return "Registartion info: " + username +
                " " + password
                + " " + name
                + " " + email;
    }
}

