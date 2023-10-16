package com.example.school_management_system.repository;

import com.example.school_management_system.dto.StudentRequest;
import com.example.school_management_system.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withDatabaseName("school_management_system")
            .withUsername("testuser")
            .withPassword("pass");

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldSaveStudent(){
        Student expectedStudent  = new Student(1L, "Seungwon Lee", 12, null);
        Student actualStudent = studentRepository.save(expectedStudent);
        Assertions.assertThat(actualStudent)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedStudent);
    }
}
