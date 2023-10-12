package com.example.school_management_system.controller;

import com.example.school_management_system.dto.GradeRequest;
import com.example.school_management_system.dto.GradeResponse;
import com.example.school_management_system.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping
    public List<GradeResponse> getGradeByAssignment(@RequestParam Long assignmentId){
        return gradeService.getGradeByAssignment(assignmentId);
    }

    @PostMapping
    public void createGrade(@RequestBody @Valid GradeRequest gradeRequest){
        gradeService.createGrade(gradeRequest);
    }

    @GetMapping("/student")
    public List<GradeResponse> getGradeByStudent(@RequestParam Long studentId){
        return gradeService.getGradeByStudent(studentId);
    }
}
