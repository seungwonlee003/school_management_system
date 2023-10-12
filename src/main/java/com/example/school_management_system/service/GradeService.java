package com.example.school_management_system.service;

import com.example.school_management_system.dto.GradeRequest;
import com.example.school_management_system.dto.GradeResponse;
import com.example.school_management_system.exceptions.AssignmentNotFoundException;
import com.example.school_management_system.exceptions.StudentNotFoundException;
import com.example.school_management_system.mapper.GradeMapper;
import com.example.school_management_system.model.Assignment;
import com.example.school_management_system.model.Student;
import com.example.school_management_system.model.Subject;
import com.example.school_management_system.repository.AssignmentRepository;
import com.example.school_management_system.repository.GradeRepository;
import com.example.school_management_system.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeService {
    @Autowired
    private final GradeRepository gradeRepository;
    @Autowired
    private final AssignmentRepository assignmentRepository;
    @Autowired
    private final GradeMapper gradeMapper;
    @Autowired
    private final StudentRepository studentRepository;

    public void createGrade(GradeRequest gradeRequest) {
        Assignment assignment = assignmentRepository.findById(gradeRequest.getAssignmentId())
                .orElseThrow(() -> new AssignmentNotFoundException(gradeRequest.getAssignmentId().toString()));
        Student student = studentRepository.findById(gradeRequest.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(gradeRequest.getStudentId().toString()));
        Subject subject = assignment.getSubject();
        boolean isStudentInSubject = subject.getStudents()
                .stream()
                .anyMatch(s -> s.getId().equals(student.getId()));
        //student not enrolled in the specific subject
        if (!isStudentInSubject) {
            throw new IllegalArgumentException();
        }
        gradeRepository.save(gradeMapper.mapToEntity(gradeRequest, student, assignment));
    }
    public List<GradeResponse> getGradeByAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new AssignmentNotFoundException(assignmentId.toString()));
        return gradeRepository.findAllByAssignment(assignment)
                .stream()
                .map(asm -> gradeMapper.mapToDto(asm))
                .collect(Collectors.toList());
    }
    public List<GradeResponse> getGradeByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId.toString()));
        return gradeRepository.findAllByStudent(student)
                .stream()
                .map(grade -> gradeMapper.mapToDto(grade))
                .collect(Collectors.toList());
    }
}
