package com.example.school_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "subjectId", referencedColumnName = "id")
    private Subject subject;
    @OneToMany(mappedBy = "assignment")
    private List<Grade> grades;
}
