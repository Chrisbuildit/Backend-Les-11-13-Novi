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

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }
//Vervangt door bovenst. constructor
   /* @Autowired
    private TeacherRepository repos;
*/
//    @GetMapping
//    public ResponseEntity<Iterable<Teacher>> getTeachers() {
//        return ResponseEntity.ok(repos.findAll());
//        }

    @GetMapping
    public ResponseEntity<Object> getTeacher(@RequestParam Long Id) {
        return ResponseEntity.ok(service.getTeacher(Id));
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
                Long newId = service.createTeacher(teacherDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/" + newId).toUriString());
                return ResponseEntity.created(uri).body(newId);
            }
        }

//    @GetMapping("/before")
//    public ResponseEntity<Iterable<Teacher>> getTeacherBefore(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(repos.findByDobBefore(date));
//    }
}
