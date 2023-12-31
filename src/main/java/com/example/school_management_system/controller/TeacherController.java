package com.example.school_management_system.controller;

import com.example.school_management_system.dto.TeacherRequest;
import com.example.school_management_system.dto.TeacherResponse;
import com.example.school_management_system.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Void> createTeacher(@RequestBody @Valid TeacherRequest teacherRequest){
        teacherService.createTeacher(teacherRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeacher(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(teacherService.getAllTeacher());
    }
}
