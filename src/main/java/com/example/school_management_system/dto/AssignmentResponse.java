package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentResponse {
    private Long assignmentId;
    private Long subjectId;
    private String name;
    private String description;
}
