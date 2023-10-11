package com.example.school_management_system.dto;

import com.example.school_management_system.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherRequest {
    private String name;
}
