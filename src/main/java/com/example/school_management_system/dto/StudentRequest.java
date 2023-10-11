package com.example.school_management_system.dto;

import com.example.school_management_system.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String name;
    private int gradeLevel;
}
