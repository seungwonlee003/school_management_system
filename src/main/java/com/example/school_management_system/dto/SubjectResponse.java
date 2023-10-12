package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectResponse {
    private Long id;
    private String name;
    private Long teacherId;
    private List<String> students;
}
