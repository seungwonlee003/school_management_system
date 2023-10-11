package com.example.school_management_system.controller;

import com.example.school_management_system.dto.SubjectRequest;
import com.example.school_management_system.dto.SubjectResponse;
import com.example.school_management_system.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private final SubjectService subjectService;

    @PostMapping
    public void createSubject(@RequestBody SubjectRequest subjectRequest){
        subjectService.createSubject(subjectRequest);
    }

    @GetMapping
    public List<SubjectResponse> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @PatchMapping("/assign-student")
    public void assignStudentToSubject(@RequestParam Long studentId,
                                       @RequestParam Long subjectId){
        subjectService.assignStudentToSubject(studentId, subjectId);
    }

    // in case teacher assigned to subject changes
    @PatchMapping("/assign-teacher")
    public void assignTeacherToSubject(@RequestParam Long teacherId,
                                       @RequestParam Long subjectId){
        subjectService.assignTeacherToSubject(teacherId, subjectId);
    }
}
