package com.example.les11repositoryandentityclass.service;

import com.example.les11repositoryandentityclass.dto.TeacherDto;
import com.example.les11repositoryandentityclass.exception.ResourceNotFoundException;
import com.example.les11repositoryandentityclass.model.Teacher;
import com.example.les11repositoryandentityclass.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }
    public TeacherDto getTeacher(Long Id) {
        Teacher t = repos.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Teacher not Found"));

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.id = t.getId();
        teacherDto.firstName = t.getFirstName();
        teacherDto.lastName = t.getLastName();
        teacherDto.dob = t.getDob();

        return teacherDto;
    }

    public List<TeacherDto> getAllTeachers() {
        Iterable<Teacher> tList = repos.findAll();
        List<TeacherDto> tDtoList = new ArrayList<>();

        for(Teacher t : tList) {
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.id = t.getId();
            teacherDto.firstName = t.getFirstName();
            teacherDto.lastName = t.getLastName();
            teacherDto.dob = t.getDob();
            tDtoList.add(teacherDto);
        }
        return tDtoList;
    }

    public Long createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        repos.save(teacher);

        return teacher.getId();
    }
}
