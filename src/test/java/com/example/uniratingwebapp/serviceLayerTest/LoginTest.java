package com.example.uniratingwebapp.serviceLayerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.uniratingwebapp.DTOs.LoginResponseDTO;
import com.example.uniratingwebapp.entities.Student;
import com.example.uniratingwebapp.repositories.StudentRepository;
import com.example.uniratingwebapp.services.AuthenticationService;
import com.example.uniratingwebapp.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
@SpringBootTest
public class LoginTest {

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void testLoginUserSuccess() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        Authentication authenticationMock = mock(Authentication.class);
        UserDetails userDetailsMock = mock(UserDetails.class);

        when(authenticationManager.authenticate(any()))
                .thenReturn(authenticationMock);

        when(tokenService.generateJwt(authenticationMock))
                .thenReturn("testToken");

        when(studentRepository.findByUsername(username))
                .thenReturn(Optional.of(new Student()));

        // Act
        LoginResponseDTO response = authenticationService.loginUser(username, password);

        // Assert
        assertTrue(response.isSuccess());
        assertNotNull(response.getToken());
        assertNotNull(response.getUser());
    }

    @Test
    void testLoginUserAuthenticationException() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        when(authenticationManager.authenticate(any()))
                .thenThrow(new AuthenticationException("Authentication failed.") {});

        // Act
        LoginResponseDTO response = authenticationService.loginUser(username, password);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("", response.getToken());
        assertNull(response.getUser());
    }

    @Test
    void testLoginUserUserDetailsNotFound() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        when(authenticationManager.authenticate(any()))
                .thenReturn(mock(Authentication.class));

        when(tokenService.generateJwt(any()))
                .thenThrow(new UsernameNotFoundException("User not found."));

        // Act
        LoginResponseDTO response = authenticationService.loginUser(username, password);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("", response.getToken());
        assertNull(response.getUser());
    }
}