package com.example.school_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectRequest {
    private String name;
    private Long teacherId;
}
