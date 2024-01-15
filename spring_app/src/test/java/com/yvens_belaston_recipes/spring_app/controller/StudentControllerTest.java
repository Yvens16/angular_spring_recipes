package com.yvens_belaston_recipes.spring_app.controller;

import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.yvens_belaston_recipes.spring_app.dto.StudentDto;
import com.yvens_belaston_recipes.spring_app.entity.Student;
import com.yvens_belaston_recipes.spring_app.service.StudentService;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = StudentController.class) // Will configure only those beans (@Controller, @ControllerAdvice,//
                                                   // @JsonComponent) and Spring security
@AutoConfigureMockMvc(addFilters = false) // Deactivate filters in spring security
@ExtendWith(MockitoExtension.class) // Register the Mockito Bean so I can mock stuff
public class StudentControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService studentService;

  @Autowired
  private ObjectMapper objectMapper;

  private Student student;
  private StudentDto studentDto;

  @BeforeEach
  public void init() {
    student = new Student("Yvens", 27);
    studentDto = new StudentDto("Yvens", 27);
  }

  @Test
  public void getStudentsController() throws Exception {
    StudentDto studentDto2 = new StudentDto("Marc", 14);
    List<StudentDto> students = new ArrayList<>();
    students.add(this.studentDto);
    students.add(studentDto2);
    when(studentService.getStudents()).thenReturn(students);
    ResultActions response = this.mockMvc.perform(
        get("/students")
            .contentType(MediaType.APPLICATION_JSON));

    response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(students.size())));
  }

  @Test
  public void createStudentController() throws Exception {
    StudentDto studentDto = new StudentDto("Marc", 14);

    // The 2 method below do the exact same thing but the first won't work if in the test the exact same instance of studentDto is not pass
    // As you can see the instance is altered by the method objectMapper.writeValueAsString so the test don't understnd that it is the same instance even tough it is the same value
    // when(studentService.createStudent(studentDto)).thenReturn(studentDto);
    given(studentService.createStudent(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

    ResultActions response = this.mockMvc.perform(
        post("/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(studentDto)) // the body of the request ,
                                                                  // objectMapper.writeValueAsString transform data into
                                                                  // a json string reprensentation.
    );

    response
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.age", CoreMatchers.is(studentDto.getAge())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(studentDto.getName())))
        .andDo(print());
  }

}
