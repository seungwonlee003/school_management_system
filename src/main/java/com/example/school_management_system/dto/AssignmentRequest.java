package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentRequest {
    @NotNull
    private Long subjectId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
