package com.example.uniratingwebapp.controllers;

import com.example.uniratingwebapp.services.UserService;
import org.hibernate.loader.LoaderLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String helloUserController(Principal principal){
        Long studentId = userService.findByUsername(principal.getName()).getId();
        return "User access granted! " + studentId;
    }
}