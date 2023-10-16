package com.example.school_management_system.service;

import com.example.school_management_system.dto.StudentResponse;
import com.example.school_management_system.exceptions.StudentNotFoundException;
import com.example.school_management_system.mapper.StudentMapper;
import com.example.school_management_system.model.Student;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.repository.SubjectRepository;
import com.example.school_management_system.repository.TeacherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentMapper studentMapper;
    @Mock
    SubjectRepository subjectRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    TeacherRepository teacherRepository;

    private StudentService studentService;

    @BeforeEach
    public void setup() {
        studentService = new StudentService(studentMapper, subjectRepository, studentRepository, teacherRepository);
    }

    @Test
    void getStudentById() {
        Student student = new Student(1L, "Seungwon Lee", 12, null);
        StudentResponse studentResponse= new StudentResponse(1L, "Seungwon Lee", 12, null);
        given(studentRepository.findById(1L)).willReturn(Optional.of(student));
        given(studentMapper.mapToDto(student)).willReturn(studentResponse);

        StudentResponse studentResponseActual = studentService.getStudentById(1L);
        Assertions.assertThat(studentResponseActual).isEqualTo(studentResponse);
    }

    @Test
    void getStudentByNonExistingIdShouldThrowException() {
        Long nonExistingId = 99L;
        given(studentRepository.findById(nonExistingId)).willReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> studentService.getStudentById(nonExistingId))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining(nonExistingId.toString());
    }

}