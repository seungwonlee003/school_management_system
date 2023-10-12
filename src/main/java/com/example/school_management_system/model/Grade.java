package com.example.school_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double grade;
    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "assignmentId", referencedColumnName = "id")
    private Assignment assignment;
}
