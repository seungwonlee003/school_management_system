package com.example.school_management_system.controller;

import com.example.school_management_system.dto.StudentRequest;
import com.example.school_management_system.dto.StudentResponse;
import com.example.school_management_system.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should create post when making POST request to endpoint")
    void shouldCreatePost() throws Exception{
        StudentRequest studentRequest = new StudentRequest("Seungwon Lee", 12);
        String json = new ObjectMapper().writeValueAsString(studentRequest);

        mockMvc.perform(
                post("/api/student")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should get all students when making GET request to endpoint")
    void shouldGetListOfPost() throws Exception{
        StudentResponse studentResponse = new StudentResponse(1L, "Seungwon Lee", 12, Arrays.asList("Math", "Physics"));
        StudentResponse studentResponse2 = new StudentResponse(2L, "Jungwon Lee", 7, Arrays.asList("Computer Science"));
        given(studentService.getAllStudents()).willReturn(Arrays.asList(studentResponse, studentResponse2));
        // above is equivalent to:
        // Mockito.when(studentService.getAllStudents()).thenReturn(Arrays.asList(studentResponse, studentResponse2));

        mockMvc.perform(
                get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Seungwon Lee")))
                .andExpect(jsonPath("$[0].gradeLevel", Matchers.is(12)))
                .andExpect(jsonPath("$[0].subjects[0]", Matchers.is("Math")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].name", Matchers.is("Jungwon Lee")))
                .andExpect(jsonPath("$[1].gradeLevel", Matchers.is(7)))
                .andExpect(jsonPath("$[1].subjects[0]", Matchers.is("Computer Science")));
    }

    @Test
    @DisplayName("should get student by Id when making GET request to endpoint")
    void shouldGetStudentById() throws Exception{
        StudentResponse studentResponse = new StudentResponse(1L, "Seungwon Lee", 12, Arrays.asList("Math", "Physics"));
        given(studentService.getStudentById(1L)).willReturn(studentResponse);
        mockMvc.perform(
                get("/api/student/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Seungwon Lee")))
                .andExpect(jsonPath("$.gradeLevel", Matchers.is(12)))
                .andExpect(jsonPath("$.subjects[0]", Matchers.is("Math")));
    }
}
