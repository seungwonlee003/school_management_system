package com.example.school_management_system.repository;

import com.example.school_management_system.dto.StudentRequest;
import com.example.school_management_system.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest extends AbstractContainerBaseTest{
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
