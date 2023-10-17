package com.example.school_management_system.integration;

import com.example.school_management_system.dto.StudentResponse;
import com.example.school_management_system.model.Student;
import com.example.school_management_system.repository.AbstractContainerBaseTest;
import com.example.school_management_system.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIntegrationTest extends AbstractContainerBaseTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @LocalServerPort
    private int randomServerPort;

    private String baseUrl = "http://localhost";

    @BeforeEach
    void setupTestData() {
        Student student1 = new Student(1L, "Seungwon Lee", 12, null);
        Student student2 = new Student(2L, "Jungwon Lee", 12, null);
        Student student3 = new Student(3L, "Jason", 12, null);
        Student student4 = new Student(4L, "Chris", 12, null);
        studentRepository.saveAll(Arrays.asList(student1, student2, student3, student4));
        baseUrl = baseUrl.concat(":").concat(randomServerPort + "").concat("/api/student");
    }

    @Test
    void shouldFindAllStudent() {
        StudentResponse[] studentResponses = restTemplate
                .getForObject(baseUrl, StudentResponse[].class);
        Assertions.assertThat(studentResponses.length).isEqualTo(4);
    }

    @Test
    void shouldFindStudentWithValidStudentId() {
        ResponseEntity<StudentResponse> response = restTemplate.exchange(baseUrl.concat("/1"), HttpMethod.GET, null, StudentResponse.class);
        Assertions.assertThat(response.getBody().getName()).isEqualTo("Seungwon Lee");
    }

    @Test
    void shouldThrowStudentNotFoundWhenInvalidStudentId() {
        ResponseEntity<StudentResponse> response = restTemplate.exchange(baseUrl.concat("/5"), HttpMethod.GET, null, StudentResponse.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @Rollback
    void shouldCreateNewStudentWhenStudentIdIsValid() {
        Student student = new Student(5L, "Harry", 12, null);
        // format: (url, GET/POST/PATCH/DELETE, request body wrapped with HttpEntity, returned type.class)
        ResponseEntity<Void> response = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<Student>(student), Void.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(studentRepository.findById(5L).get().getName()).isEqualTo(student.getName());
    }
}
