package com.example.les11repositoryandentityclass.repository;

import com.example.les11repositoryandentityclass.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {

        Iterable<Teacher> findByDobBefore (LocalDate date);
}
