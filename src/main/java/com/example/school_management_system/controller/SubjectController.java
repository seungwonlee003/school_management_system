package com.example.school_management_system.controller;

import com.example.school_management_system.dto.SubjectRequest;
import com.example.school_management_system.dto.SubjectResponse;
import com.example.school_management_system.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Void> createSubject(@RequestBody @Valid SubjectRequest subjectRequest){
        subjectService.createSubject(subjectRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponse>> getAllSubject(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAllSubject());
    }

    @GetMapping("/by-teacher")
    public ResponseEntity<List<SubjectResponse>> getAllSubjectByTeacher(@RequestParam Long teacherId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAllSubjectByTeacher(teacherId));
    }

    @PatchMapping("/assign-student")
    public ResponseEntity<Void> assignStudentToSubject(@RequestParam Long studentId,
                                       @RequestParam Long subjectId){
        subjectService.assignStudentToSubject(studentId, subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/assign-teacher")
    public ResponseEntity<Void> assignTeacherToSubject(@RequestParam Long teacherId,
                                       @RequestParam Long subjectId){
        subjectService.assignTeacherToSubject(teacherId, subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
