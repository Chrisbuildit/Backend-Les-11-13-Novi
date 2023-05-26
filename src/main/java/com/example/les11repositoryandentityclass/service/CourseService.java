package com.example.les11repositoryandentityclass.service;

import com.example.les11repositoryandentityclass.dto.CourseDto;
import com.example.les11repositoryandentityclass.dto.TeacherDto;
import com.example.les11repositoryandentityclass.exception.ResourceNotFoundException;
import com.example.les11repositoryandentityclass.model.Course;
import com.example.les11repositoryandentityclass.model.Teacher;
import com.example.les11repositoryandentityclass.repository.CourseRepository;
import com.example.les11repositoryandentityclass.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepos;
    private final TeacherRepository teacherRepos;

    public CourseService(CourseRepository courseRepos, TeacherRepository teacherRepos) {
        this.courseRepos = courseRepos;
        this.teacherRepos = teacherRepos;
    }

    public List<CourseDto> getAllCourses() {
        Iterable<Course> cList = courseRepos.findAll();
        List<CourseDto> cDtoList = new ArrayList<>();

        for(Course c : cList) {
            CourseDto courseDto = new CourseDto();
            courseDto.id = c.getId();
            courseDto.title = c.getTitle();
            courseDto.sp = c.getSp();
            courseDto.teacherId = c.getTeacher().getId();
            cDtoList.add(courseDto);
        }
        return cDtoList;
    }
    public Long createCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setTitle(courseDto.title);
        course.setSp(courseDto.sp);

        Teacher teacher = teacherRepos.findById(courseDto.teacherId).get(); //happy flow
        course.setTeacher(teacher);
        courseRepos.save(course);

        return course.getId();
    }
}
