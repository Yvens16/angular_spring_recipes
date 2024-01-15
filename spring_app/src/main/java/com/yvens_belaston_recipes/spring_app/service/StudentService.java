package com.yvens_belaston_recipes.spring_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvens_belaston_recipes.spring_app.dto.StudentDto;
import com.yvens_belaston_recipes.spring_app.entity.Student;
import com.yvens_belaston_recipes.spring_app.repository.StudentRepository;

@Service
public class StudentService {
  private StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public StudentDto createStudent(StudentDto studentDto) {
    Student student = new Student(studentDto.getName(), studentDto.getAge());
    Student savedStudent = this.studentRepository.save(student);
    StudentDto studentDtoToReturn = new StudentDto(savedStudent.getName(), savedStudent.getAge());
    System.out.println("@@@@@@@@@@ : " + studentDtoToReturn.getName());
    return studentDtoToReturn;
  }

  public List<StudentDto> getStudents() {
    List<Student> students = this.studentRepository.findAll();
    List<StudentDto> studentDtos = new ArrayList<>();
    for (Student student : students) {
      StudentDto studentDto = new StudentDto(student.getName(), student.getAge());
      studentDtos.add(studentDto);
    }

    return studentDtos;
  }

}
