package com.example.school_management_system.service;

import com.example.school_management_system.dto.StudentRequest;
import com.example.school_management_system.dto.StudentResponse;
import com.example.school_management_system.mapper.StudentMapper;
import com.example.school_management_system.model.Student;
import com.example.school_management_system.model.Subject;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    private final StudentMapper studentMapper;
    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private final StudentRepository studentRepository;
    public void createStudent(StudentRequest studentRequest) {
        studentRepository.save(studentMapper.mapToEntity(studentRequest));
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> studentMapper.mapToDto(student))
                .collect(Collectors.toList());
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return studentMapper.mapToDto(student);
    }

    public List<StudentResponse> getAllStudentsBySubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException());
        Set<Student> studentSet = subject.getStudents();
        return studentSet
                .stream()
                .map(student -> studentMapper.mapToDto(student))
                .collect(Collectors.toList());
    }

}
