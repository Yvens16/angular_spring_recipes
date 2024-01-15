package com.yvens_belaston_recipes.spring_app.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
// import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yvens_belaston_recipes.spring_app.dto.StudentDto;
import com.yvens_belaston_recipes.spring_app.entity.Student;
import com.yvens_belaston_recipes.spring_app.repository.StudentRepository;

// let us register extension for the current test class
@ExtendWith(MockitoExtension.class)
// @RunWith(MockitoJUnitRunner.class) // Switch the default Test runner with the MockitoJUnitRunner, which in turn automatically init Fields mark with @Mock annotation in Junit4 not 5 where we use ExtendWith
public class StudentServiceTest {
  // Create the instance of StudentService and automatically inject the studentRepository mock
  @InjectMocks
  private StudentService studentService;
  
  @Mock // Create  a mock version of the object
  private StudentRepository studentRepository;


  @Test
  public void createStudentService() {
    Student student = new Student("Yvens", 27);
    StudentDto studentDto = new StudentDto("Yvens", 27);
    when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

    StudentDto savedStudent = studentService.createStudent(studentDto);
    Assertions.assertThat(savedStudent.getName()).isEqualTo("Yvens");
  }

  @Test
  public void getStudentService() {
    Student student = new Student("Yvens", 27);
    Student student2 = new Student("Marc", 14);
    List<Student> students = new ArrayList<>();
    students.add(student);
    students.add(student2);
    when(studentRepository.findAll()).thenReturn(students);
    List<StudentDto> returnedStudents = studentService.getStudents();
    Assertions.assertThat(returnedStudents.size()).isEqualTo(2);
  }
}
