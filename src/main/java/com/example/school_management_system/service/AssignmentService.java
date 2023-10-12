package com.example.school_management_system.service;

import com.example.school_management_system.dto.AssignmentRequest;
import com.example.school_management_system.dto.AssignmentResponse;
import com.example.school_management_system.mapper.AssignmentMapper;
import com.example.school_management_system.model.Assignment;
import com.example.school_management_system.model.Subject;
import com.example.school_management_system.repository.AssignmentRepository;
import com.example.school_management_system.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssignmentService {
    @Autowired
    private final AssignmentRepository assignmentRepository;
    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private final AssignmentMapper assignmentMapper;

    public void createAssignment(AssignmentRequest assignmentRequest) {
        Subject subject = subjectRepository.findById(assignmentRequest.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException());
        assignmentRepository.save(assignmentMapper.mapToEntity(assignmentRequest, subject));
    }

    public List<AssignmentResponse> getAssignmentBySubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException());
        List<Assignment> assignments = assignmentRepository.findAllBySubject(subject);
        return assignments.stream()
                .map(assignment -> assignmentMapper.mapToDto(assignment))
                .collect(Collectors.toList());
    }
}
