package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequest {
    @NotNull
    private Double grade;
    @NotNull
    private Long studentId;
    @NotNull
    private Long assignmentId;
}
