package com.example.les11repositoryandentityclass.service;

import com.example.les11repositoryandentityclass.dto.TeacherDto;
import com.example.les11repositoryandentityclass.exception.ResourceNotFoundException;
import com.example.les11repositoryandentityclass.model.Teacher;
import com.example.les11repositoryandentityclass.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepos;

    public TeacherService(TeacherRepository teacherRepos) {
        this.teacherRepos = teacherRepos;
    }

    //Short version
    public TeacherDto getTeacher(Long Id) {
        Teacher t = teacherRepos.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Teacher not Found"));

        return transferToDto(t);
    }

    //Short version
    public List<TeacherDto> getAllTeachers() {
        Iterable<Teacher> tList = teacherRepos.findAll();
        List<TeacherDto> tDtoList = new ArrayList<>();

        for(Teacher t : tList) {
            TeacherDto teacherDto = transferToDto(t);
            tDtoList.add(teacherDto);
        }
        return tDtoList;
    }

    public List<TeacherDto> getTeacherBefore(LocalDate date) {
        Iterable<Teacher> tList = teacherRepos.findByDobBefore(date);
        List<TeacherDto> teachersBefore = new ArrayList<>();

        for(Teacher t : tList) {
            TeacherDto teacherDto = transferToDto(t);
            teachersBefore.add(teacherDto);
        }
        return teachersBefore;
    }

    public Long createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        teacherRepos.save(teacher);

        return teacher.getId();
    }

    public TeacherDto transferToDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.id = teacher.getId();
        teacherDto.firstName = teacher.getFirstName();
        teacherDto.lastName = teacher.getLastName();
        teacherDto.dob = teacher.getDob();

        return teacherDto;
    }
}
