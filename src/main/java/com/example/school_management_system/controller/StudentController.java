package com.example.school_management_system.controller;

import com.example.school_management_system.dto.StudentRequest;
import com.example.school_management_system.dto.StudentResponse;
import com.example.school_management_system.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createStudent(@RequestBody @Valid StudentRequest studentRequest){
        studentService.createStudent(studentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.getStudentById(id));
    }

    @GetMapping("/students-by-subject")
    public ResponseEntity<List<StudentResponse>> getAllStudentsBySubject(@RequestParam Long subjectId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.getAllStudentsBySubject(subjectId));
    }

    // not recommended to use due to heavy custom operations on java code
    @GetMapping("/students-by-teacher")
    public ResponseEntity<List<StudentResponse>> getAllStudentsByTeacher(@RequestParam Long teacherId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.getAllStudentsByTeacher(teacherId));
    }
}
