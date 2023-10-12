package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherRequest {
    @NotBlank
    private String name;
}
