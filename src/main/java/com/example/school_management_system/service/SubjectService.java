package com.example.school_management_system.service;

import com.example.school_management_system.dto.SubjectRequest;
import com.example.school_management_system.dto.SubjectResponse;
import com.example.school_management_system.mapper.SubjectMapper;
import com.example.school_management_system.model.Student;
import com.example.school_management_system.model.Subject;
import com.example.school_management_system.model.Teacher;
import com.example.school_management_system.repository.StudentRepository;
import com.example.school_management_system.repository.SubjectRepository;
import com.example.school_management_system.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {
    @Autowired
    private final SubjectMapper subjectMapper;
    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private final TeacherRepository teacherRepository;
    @Autowired
    private final StudentRepository studentRepository;
    public void createSubject(SubjectRequest subjectRequest){
        Teacher teacher = teacherRepository.findById(subjectRequest.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException());
        subjectRepository.save(subjectMapper.mapToEntity(subjectRequest, teacher));
    }
    public List<SubjectResponse> getAllSubject(){
        return subjectRepository.findAll().stream()
                .map(subject -> subjectMapper.mapToDto(subject))
                .collect(Collectors.toList());
    }
    public void assignStudentToSubject(Long studentId, Long subjectId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException());
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException());
        subject.getStudents().add(student);
        student.getSubjects().add(subject);
        // Save the changes to both entities
        subjectRepository.save(subject);
        studentRepository.save(student);
    }

    public void assignTeacherToSubject(@RequestParam Long teacherId,
                                       @RequestParam Long subjectId){
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException());
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException());
        subject.setTeacher(teacher);
        teacher.getSubjects().add(subject);
        // Save the changes to both entities
        teacherRepository.save(teacher);
        subjectRepository.save(subject);
    }
}
