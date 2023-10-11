package com.example.school_management_system.controller;

import com.example.school_management_system.dto.StudentRequest;
import com.example.school_management_system.dto.StudentResponse;
import com.example.school_management_system.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @PostMapping
    public void createStudent(@RequestBody StudentRequest studentRequest){
        studentService.createStudent(studentRequest);
    }
    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/students-by-subject")
    public List<StudentResponse> getAllStudentsBySubject(@RequestParam Long subjectId){
        return studentService.getAllStudentsBySubject(subjectId);
    }
}
