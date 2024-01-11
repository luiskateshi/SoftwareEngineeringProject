package com.example.uniratingwebapp;

import com.example.uniratingwebapp.entities.Student;
import com.example.uniratingwebapp.entities.Role;
import com.example.uniratingwebapp.repositories.RoleRepository;
import com.example.uniratingwebapp.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SoftEngProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftEngProjectApplication.class, args);
    }


    @Bean
    CommandLineRunner run(RoleRepository roleRepository, StudentRepository studentRepository, PasswordEncoder passwordEncode){
        return args ->{
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

//            Student admin = new Student(1,"admin", passwordEncode.encode("admin"), roles, "student1", "student1@gmail.com");
            Student admin = new Student(1L,"student1", "student1@gmail.com", "admin", passwordEncode.encode("password"), roles );

            studentRepository.save(admin);
        };
    }
}
