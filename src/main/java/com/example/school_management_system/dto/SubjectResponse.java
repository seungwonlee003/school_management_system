package com.example.school_management_system.dto;

import com.example.school_management_system.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectResponse {
    private Long id;
    private String name;
    private Long teacherId;
    private List<String> students;
}
