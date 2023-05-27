package com.example.les11repositoryandentityclass.controller;

import com.example.les11repositoryandentityclass.dto.TeacherDto;
import com.example.les11repositoryandentityclass.model.Teacher;
import com.example.les11repositoryandentityclass.repository.TeacherRepository;
import com.example.les11repositoryandentityclass.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
        }

    @GetMapping("/{Id}")
    public ResponseEntity<Object> getTeacher(@PathVariable Long Id) {
        return ResponseEntity.ok(teacherService.getTeacher(Id));
    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) {
        if(br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                //Create new line
                sb.append("\n");
            }
            return  ResponseEntity.badRequest().body(sb.toString());
        } else {
                Long newId = teacherService.createTeacher(teacherDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/" + newId).toUriString());
                return ResponseEntity.created(uri).body(newId);
            }
        }

    @GetMapping("/before")
    public ResponseEntity<List<TeacherDto>> getTeacherBefore(@RequestParam LocalDate date) {
        return ResponseEntity.ok(teacherService.getTeacherBefore(date));
    }
}
