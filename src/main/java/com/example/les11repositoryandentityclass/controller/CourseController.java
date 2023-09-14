package com.example.les11repositoryandentityclass.controller;

import com.example.les11repositoryandentityclass.dto.CourseDto;
import com.example.les11repositoryandentityclass.dto.TeacherDto;
import com.example.les11repositoryandentityclass.model.Course;
import com.example.les11repositoryandentityclass.model.Teacher;
import com.example.les11repositoryandentityclass.repository.CourseRepository;
import com.example.les11repositoryandentityclass.repository.TeacherRepository;
import com.example.les11repositoryandentityclass.service.CourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Object> getCourse(@PathVariable Long Id) {
        return ResponseEntity.ok(courseService.getCourse(Id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    //Maakt een object aan van ResponseEntity
    @PostMapping
    public ResponseEntity<Long> createCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto newCourse) {
        CourseDto dto = courseService.updateCourse(id, newCourse);
        return ResponseEntity.ok().body(dto);
    }
}
