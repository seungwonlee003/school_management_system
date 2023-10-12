package com.example.school_management_system.repository;

import com.example.school_management_system.model.Assignment;
import com.example.school_management_system.model.Grade;
import com.example.school_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByAssignment(Assignment assignment);
    List<Grade> findAllByStudent(Student student);
}
