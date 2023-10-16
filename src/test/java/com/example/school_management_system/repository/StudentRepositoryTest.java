package com.example.school_management_system.repository;

import com.example.school_management_system.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

// it is not a spring boot test
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
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

    @Test
    void shouldGetAllStudents() {
        Student student1 = new Student(2L, "Kung Lee", 12, null);
        Student student2 = new Student(3L, "Jungwon Lee", 12, null);
        Student student3 = new Student(4L, "Jungkok Lee", 12, null);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        List<Student> expectedStudents = Arrays.asList(student1, student2, student3);
        List<Student> actualStudents = studentRepository.findAll();
        Assertions.assertThat(expectedStudents.size()).isEqualTo(actualStudents.size());
    }

    @Test
    void shouldGetStudentById(){
        Student expectedStudent = new Student(1L, "Seungwon Lee", 12, null);
        Student student = studentRepository.save(expectedStudent);
        Student actualStudent = studentRepository.findById(student.getId()).orElse(null);
        Assertions.assertThat(actualStudent)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedStudent);
    }
}
