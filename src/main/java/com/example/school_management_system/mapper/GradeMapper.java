package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.GradeRequest;
import com.example.school_management_system.dto.GradeResponse;
import com.example.school_management_system.model.Assignment;
import com.example.school_management_system.model.Grade;
import com.example.school_management_system.model.Student;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {
    public Grade mapToEntity(GradeRequest gradeRequest, Student student, Assignment assignment){
        Grade grade = new Grade();
        grade.setGrade(gradeRequest.getGrade());
        grade.setAssignment(assignment);
        grade.setStudent(student);
        return grade;
    }

    public GradeResponse mapToDto(Grade grade){
        GradeResponse gradeResponse = new GradeResponse();
        gradeResponse.setId(grade.getId());
        gradeResponse.setGrade(grade.getGrade());
        gradeResponse.setStudentId(grade.getStudent().getId());
        gradeResponse.setAssignmentId(grade.getAssignment().getId());
        gradeResponse.setSubjectId(grade.getId());
        return gradeResponse;
    }
}
