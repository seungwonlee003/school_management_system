package com.example.school_management_system.controller;

import com.example.school_management_system.dto.AssignmentRequest;
import com.example.school_management_system.dto.AssignmentResponse;
import com.example.school_management_system.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/assignment")
public class AssignmentController {
    @Autowired
    private final AssignmentService assignmentService;

    @PostMapping
    public void createAssignment(@RequestBody @Valid AssignmentRequest assignmentRequest){
        assignmentService.createAssignment(assignmentRequest);
    }
    @GetMapping
    public List<AssignmentResponse> getAssignmentBySubject(@RequestParam Long subjectId){
        return assignmentService.getAssignmentBySubject(subjectId);
    }
}
