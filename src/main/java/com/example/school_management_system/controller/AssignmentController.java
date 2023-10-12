package com.example.school_management_system.controller;

import com.example.school_management_system.dto.AssignmentRequest;
import com.example.school_management_system.dto.AssignmentResponse;
import com.example.school_management_system.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/assignment")
public class AssignmentController {
    @Autowired
    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Void> createAssignment(@RequestBody @Valid AssignmentRequest assignmentRequest){
        assignmentService.createAssignment(assignmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AssignmentResponse>> getAssignmentBySubject(@RequestParam Long subjectId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(assignmentService.getAssignmentBySubject(subjectId));
    }
}
