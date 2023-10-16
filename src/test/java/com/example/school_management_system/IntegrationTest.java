package com.example.school_management_system;

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
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
@Slf4j
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest extends AbstractContainerBaseTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    StudentRepository studentRepository;

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    void setUpData(){
        Student student1 = new Student(1L, "Seungwon Lee", 12, null);
        Student student2 = new Student(2L, "Jungwon Lee", 12, null);
        Student student3 = new Student(3L, "Jason", 12, null);
        Student student4 = new Student(4L, "Chris", 12, null);
        studentRepository.saveAll(Arrays.asList(student1, student2, student3, student4));
    }

    @Test
    void shouldFindAllStudent(){
        StudentResponse[] studentResponses = restTemplate
                .getForObject("http://localhost:" + randomServerPort + "/api/student", StudentResponse[].class);
        Assertions.assertThat(studentResponses.length).isEqualTo(4);
    }

    @Test
    void shouldFindStudentWithValidStudentId(){
        ResponseEntity<StudentResponse> response = restTemplate.exchange("http://localhost:" + randomServerPort
                + "/api/student/1", HttpMethod.GET, null, StudentResponse.class);
        Assertions.assertThat(response.getBody().getName()).isEqualTo("Seungwon Lee");
    }

    @Test
    void shouldThrowStudentNotFoundWhenInvalidStudentId(){
        ResponseEntity<StudentResponse> response = restTemplate.exchange("http://localhost:" + randomServerPort
                + "/api/student/5", HttpMethod.GET, null, StudentResponse.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @Rollback
    void shouldCreateNewStudentWhenStudentIdIsValid(){
        Student student = new Student(5L, "Harry", 12, null);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + randomServerPort
                        + "/api/student", HttpMethod.POST, new HttpEntity<Student>(student), Void.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(studentRepository.findById(5L).get().getName()).isEqualTo(student.getName());
    }
}
