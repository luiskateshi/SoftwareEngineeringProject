package com.example.uniratingwebapp.ControllerTests;

import com.example.uniratingwebapp.controllers.CourseController;
import com.example.uniratingwebapp.entities.Course;
import com.example.uniratingwebapp.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRepository courseRepository;

    private List<Course> courses;

    @BeforeEach
    public void setUp() {
        courses = Arrays.asList(
                new Course(1L, "Mathematics", "Mathematics course", "Prof. Smith", "2023-09-01", 30, 0, "math.jpg"),
                new Course(2L, "Physics", "Physics course", "Prof. Johnson", "2023-09-01", 30, 0, "physics.jpg")
        );
    }

    @Test
    public void getAllCoursesReturnsListOfCourses() throws Exception {
        given(courseRepository.findAll()).willReturn(courses);

        mockMvc.perform(get("/courses/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'title': 'Mathematics', 'description': 'Mathematics course', 'lecturer': 'Prof. Smith', 'startDate': '2023-09-01', 'maxNumberOfStudents': 30, 'currentNumberOfStudents': 0, 'image': 'math.jpg'}, {'title': 'Physics', 'description': 'Physics course', 'lecturer': 'Prof. Johnson', 'startDate': '2023-09-01', 'maxNumberOfStudents': 30, 'currentNumberOfStudents': 0, 'image': 'physics.jpg'}]"));
    }

    @Test
    public void getAllCoursesReturnsEmptyListWhenNoCourses() throws Exception {
        given(courseRepository.findAll()).willReturn(Arrays.asList());

        mockMvc.perform(get("/courses/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}