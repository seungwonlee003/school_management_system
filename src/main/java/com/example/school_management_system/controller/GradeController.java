package com.example.school_management_system.controller;

import com.example.school_management_system.dto.GradeRequest;
import com.example.school_management_system.dto.GradeResponse;
import com.example.school_management_system.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<Void> createGrade(@RequestBody @Valid GradeRequest gradeRequest){
        gradeService.createGrade(gradeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<GradeResponse>> getGradeByAssignment(@RequestParam Long assignmentId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gradeService.getGradeByAssignment(assignmentId));
    }
    @GetMapping("/student")
    public ResponseEntity<List<GradeResponse>> getGradeByStudent(@RequestParam Long studentId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gradeService.getGradeByStudent(studentId));
    }
}
