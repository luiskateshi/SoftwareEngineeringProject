package com.example.uniratingwebapp.serviceLayerTest;

import com.example.uniratingwebapp.DTOs.LoginResponseDTO;
import com.example.uniratingwebapp.entities.Role;
import com.example.uniratingwebapp.entities.Student;
import com.example.uniratingwebapp.repositories.RoleRepository;
import com.example.uniratingwebapp.repositories.StudentRepository;
import com.example.uniratingwebapp.services.AuthenticationService;
import com.example.uniratingwebapp.services.TokenService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RegistrationServiceTest{

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    void testRegisterUser() {

        String username = "testUser";
        String password = "testPassword";
        String name = "Test User";
        String email = "test@example.com";

        Student student = Student.builder()
                .username(username)
                .password(password)
                .name(name)
                .name(name)
                .email(email)
                .build();

        // Mock behavior for findByAuthority method
        Mockito.when(roleRepository.findByAuthority("USER")).thenReturn(Optional.of(new Role("USER")));

        // Mock behavior for encode method
        Mockito.when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        // Mock behavior for authenticate method
        Authentication authenticationMock = mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(any()))
                .thenReturn(authenticationMock);

        // Mock behavior for generateJwt method
        Mockito.when(tokenService.generateJwt(authenticationMock)).thenReturn("testToken");

        // Mock behavior for findByUsername method
        Mockito.when(studentRepository.findByUsername(anyString())).thenReturn(Optional.of(new Student()));

        // Mock behavior for loginUser method
        LoginResponseDTO loginResponse = new LoginResponseDTO(student, "testToken", true);
        Mockito.when(authenticationService.loginUser(username, password)).thenReturn(loginResponse);

        // Act
        LoginResponseDTO response = authenticationService.registerUser(username, password, name, email);

        // Assert
        assertNotNull(response);
    }
}
