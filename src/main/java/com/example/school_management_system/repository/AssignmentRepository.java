package com.example.school_management_system.repository;

import com.example.school_management_system.model.Assignment;
import com.example.school_management_system.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllBySubject(Subject subject);
}
