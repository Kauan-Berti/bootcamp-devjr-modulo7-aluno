package com.abutua.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.model.Student;

@RestController
@CrossOrigin
public class StudentController {

    private List<Student> students = Arrays.asList(new Student(1, "João Silva", "joao.silva@email.com", "11999999999", 1, 1)
                                                ,new Student(2, "Maria Souza", "maria.souza@email.com", "11999999999", 2, 2)
                                                ,new Student(3, "Pedro Oliveira", "pedro.oliveira@email.com", "11999999999", 3, 3));

    @PostMapping("students")
    public ResponseEntity<Student> save(@RequestBody Student student){
        student.setId(students.size()+1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(student.getId())
                    .toUri();

        return ResponseEntity.created(location).body(student);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        Student stud = students.stream()
                                    .filter(s -> s.getId() ==id)
                                    .findFirst()
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Studnet not found"));

        return ResponseEntity.ok(stud);

    }

    @GetMapping("students")
    public List<Student> getStudent(){
        return students;
    }
}
