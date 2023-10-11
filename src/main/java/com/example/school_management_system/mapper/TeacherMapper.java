package com.example.school_management_system.mapper;

import com.example.school_management_system.dto.TeacherRequest;
import com.example.school_management_system.dto.TeacherResponse;
import com.example.school_management_system.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {
    public Teacher mapToEntity(TeacherRequest teacherRequest){
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.getName());
        return teacher;
    }

    public TeacherResponse mapToDto(Teacher teacher){
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setName(teacher.getName());
        List<String> subjects = teacher.getSubjects()
                        .stream()
                        .map(subject -> subject.getName())
                        .collect(Collectors.toList());
        teacherResponse.setSubjects(subjects);
        return teacherResponse;
    }
}
