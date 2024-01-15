package com.yvens_belaston_recipes.spring_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yvens_belaston_recipes.spring_app.dto.StudentDto;
import com.yvens_belaston_recipes.spring_app.service.StudentService;

@RestController
public class StudentController {
  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping("students")
  public ResponseEntity<List<StudentDto>> getStudents() {
    return new ResponseEntity<>(
        this.studentService.getStudents(), HttpStatus.OK);
  }

  @PostMapping("create")
  public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
    return new ResponseEntity<>(this.studentService.createStudent(studentDto),  HttpStatus.CREATED);
  }
}
