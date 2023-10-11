package com.example.school_management_system.repository;

import com.example.school_management_system.model.Student;
import com.example.school_management_system.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s JOIN s.subjects subj WHERE subj = :subject")
    List<Student> findAllBySubjects(@Param("subject") Subject subject);
}
