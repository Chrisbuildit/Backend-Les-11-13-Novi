package com.example.les11repositoryandentityclass.repository;

import com.example.les11repositoryandentityclass.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
