package com.example.school_management_system.dto;

import com.example.school_management_system.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentResponse {
    private Long id;
    private String name;
    private int gradeLevel;
    private List<String> subjects;
}
