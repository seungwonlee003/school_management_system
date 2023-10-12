package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.StudentRequest;
import com.example.school_management_system.dto.StudentResponse;
import com.example.school_management_system.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {
    public Student mapToEntity(StudentRequest studentRequest){
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setGradeLevel(studentRequest.getGradeLevel());
        return student;
    }

    public StudentResponse mapToDto(Student student){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setGradeLevel(student.getGradeLevel());
        List<String> subjectNamesList = student.getSubjects().stream()
                .map(subject -> subject.getName())
                .collect(Collectors.toList());
        studentResponse.setSubjects(subjectNamesList);
        return studentResponse;
    }
}
