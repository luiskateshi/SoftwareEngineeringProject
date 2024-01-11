package com.example.uniratingwebapp.controllers;


import com.example.uniratingwebapp.DTOs.LoginResponseDTO;
import com.example.uniratingwebapp.DTOs.RegistrationDTO;
import com.example.uniratingwebapp.entities.Student;
import com.example.uniratingwebapp.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Student registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getName(), body.getEmail());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}

