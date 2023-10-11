package com.example.school_management_system.service;

import com.example.school_management_system.controller.TeacherController;
import com.example.school_management_system.dto.TeacherRequest;
import com.example.school_management_system.dto.TeacherResponse;
import com.example.school_management_system.mapper.TeacherMapper;
import com.example.school_management_system.model.Teacher;
import com.example.school_management_system.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;
    @Autowired
    private final TeacherMapper teacherMapper;
    @PostMapping
    public void createTeacher(TeacherRequest teacherRequest) {
        teacherRepository.save(teacherMapper.mapToEntity(teacherRequest));
    }
    @GetMapping
    public List<TeacherResponse> getAllTeacher() {
        List<Teacher> teacherList = teacherRepository.findAll();
        return teacherList.stream()
                .map(teacher -> teacherMapper.mapToDto(teacher))
                .collect(Collectors.toList());
    }
}