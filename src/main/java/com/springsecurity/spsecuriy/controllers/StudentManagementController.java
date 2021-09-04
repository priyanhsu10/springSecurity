package com.springsecurity.spsecuriy.controllers;

import com.springsecurity.spsecuriy.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/management")
@PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_TRAINEE')")
public class StudentManagementController {
    private static final List<Student> students = Arrays.asList(
            new Student(1, "Shambhu"),
            new Student(2, "Golu"),
            new Student(3, "Priya")
    );
    @GetMapping

public  List<Student> getAll(){
    return  students;
}
@PostMapping
@PreAuthorize("hasAuthority('student:write')")
public  void  add(@RequestBody Student student){
    System.out.println(student);
}
@DeleteMapping("/{id}")
@PreAuthorize("hasAuthority('student:write')")
public  void  delete(@PathVariable int id){
    System.out.println(id);
}
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('student:write')")
public  void  update(@PathVariable int id, @RequestBody  Student student){
    System.out.println(student);
}
}
