package com.example.school_management_system.repository;

import com.example.school_management_system.model.Student;
import com.example.school_management_system.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
