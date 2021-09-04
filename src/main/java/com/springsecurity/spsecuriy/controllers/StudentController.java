package com.springsecurity.spsecuriy.controllers;

import com.springsecurity.spsecuriy.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/students")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentController {
    private static final List<Student> students = Arrays.asList(
            new Student(1, "Shambhu"),
            new Student(2, "Golu"),
            new Student(3, "Priya")
    );

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return students.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("student not exist"));
    }

}
