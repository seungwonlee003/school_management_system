package com.example.school_management_system.repository;

import com.example.school_management_system.model.Subject;
import com.example.school_management_system.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByTeacher(Teacher teacher);
}
