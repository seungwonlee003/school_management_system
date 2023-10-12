package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponse {
    private Long id;
    private Double grade;
    private Long studentId;
    private Long assignmentId;
    private Long subjectId;
}
