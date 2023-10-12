package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.AssignmentRequest;
import com.example.school_management_system.dto.AssignmentResponse;
import com.example.school_management_system.model.Assignment;
import com.example.school_management_system.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {
    public AssignmentResponse mapToDto(Assignment assignment){
        AssignmentResponse assignmentResponse = new AssignmentResponse();
        assignmentResponse.setAssignmentId(assignment.getId());
        assignmentResponse.setSubjectId(assignment.getSubject().getId());
        assignmentResponse.setName(assignment.getName());
        assignmentResponse.setDescription(assignment.getDescription());
        return assignmentResponse;
    }

    public Assignment mapToEntity(AssignmentRequest assignmentRequest, Subject subject){
        Assignment assignment = new Assignment();
        assignment.setName(assignmentRequest.getName());
        assignment.setDescription(assignmentRequest.getDescription());
        assignment.setSubject(subject);
        return assignment;
    }
}
