package com.example.school_management_system.dto;

import com.example.school_management_system.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherResponse {
    private Long id;
    private String name;
    private List<String> subjects;
}
