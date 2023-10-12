package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.SubjectRequest;
import com.example.school_management_system.dto.SubjectResponse;
import com.example.school_management_system.model.Subject;
import com.example.school_management_system.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@AllArgsConstructor
public class SubjectMapper {
    @Autowired
    private final StudentMapper studentMapper;
    public Subject mapToEntity(SubjectRequest subjectRequest, Teacher teacher){
        Subject subject = new Subject();
        subject.setName(subjectRequest.getName());
        subject.setTeacher(teacher);
        return subject;
    }
    public SubjectResponse mapToDto(Subject subject){
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(subject.getId());
        subjectResponse.setName(subject.getName());
        if (subject.getTeacher() != null) {
            subjectResponse.setTeacherId(subject.getTeacher().getId());
        }
        List<String> students = subject.getStudents()
                .stream()
                .map(student -> student.getName())
                .collect(Collectors.toList());
        subjectResponse.setStudents(students);
        return subjectResponse;
    }
}
