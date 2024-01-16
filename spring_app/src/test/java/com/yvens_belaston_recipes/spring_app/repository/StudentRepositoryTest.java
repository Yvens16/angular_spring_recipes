package com.yvens_belaston_recipes.spring_app.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.yvens_belaston_recipes.spring_app.entity.Student;

// Test that focuses only on JPA Component
// Changes made to the db will be undone after each test (same file or not)
// Only setting up jpa related stuff (JpaRepository, in memory db H2, JPa Related beans (Entity))
// Not setting up the services, controller, security related stuff
// Tells which type of database to use for the tests here an emmbedded h2 DB
// We could use Derby or HSQL instead of H2 in the place of H2 for an emmbedded database
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentRepositoryTest {
  private StudentRepository studentRepository;

  @Autowired // @Autowired tells Spring to inject the necessary beans at the time of object creation, thus resolving the parameters
  public StudentRepositoryTest(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Test // To signal that the method is a test method
  public void StudentSave() {
    Student student = new Student("Yvens", 27);
    Student savedStudent = this.studentRepository.save(student);
    Assertions.assertThat(savedStudent).isNotNull();
    Assertions.assertThat(savedStudent.getName()).isEqualTo("Yvens");
  }

  @Test
  public void GetStudent() {
    Student student = new Student("Yvens", 27);
    Student student2 = new Student("Marc", 23);
    this.studentRepository.save(student);
    this.studentRepository.save(student2);
    Student obtainedStudent = this.studentRepository.findById(student2.getId()).get();
    Assertions.assertThat(obtainedStudent.getName()).isEqualTo("Marc");
  }

  @Test
  public void GetAllStudents() {
    Student student = new Student("Yvens", 27);
    Student student2 = new Student("Marc", 23);
    this.studentRepository.save(student);
    this.studentRepository.save(student2);
    List<Student> studentList = this.studentRepository.findAll();
    Assertions.assertThat(studentList).isNotNull();
    Assertions.assertThat(studentList.size()).isEqualTo(2);
  }

  @Test
  public void updateStudent() {
    Student student = new Student("Yvens", 27);
    this.studentRepository.save(student);

    Student updatingStudent = this.studentRepository.findById(student.getId()).get();
    updatingStudent.setName("Yvens Belaston");

    Student savedStudent =  this.studentRepository.save(updatingStudent);
    Assertions.assertThat(savedStudent.getName()).isEqualTo("Yvens Belaston");
  }

  @Test
  public void deleteStudent() {
    Student student = new Student("Yvens", 27);
    this.studentRepository.save(student);
    this.studentRepository.deleteById(student.getId());
    Optional<Student> studentD = this.studentRepository.findById(student.getId());
    Assertions.assertThat(studentD).isEmpty();
  }

  @Test
  public void customMethod() {
    // Arrange ===> préparer l'environnement du test
    Student student = new Student("Jean", 14);
    Student student2 = new Student("Marc", 15);
    Student student3 = new Student("Baptiste", 17);
    Student student4 = new Student("Eli", 18);
    Student student5 = new Student("Yvens", 27);
    this.studentRepository.save(student);
    this.studentRepository.save(student2);
    this.studentRepository.save(student3);
    this.studentRepository.save(student4);
    this.studentRepository.save(student5);

    // Act ===> j'effectue mon action
    List<Student> students = this.studentRepository.findByAgeGreaterThanEqual(18);

    // Assert ===> Je vérifie le résultat de mon action
    Assertions.assertThat(students.size()).isEqualTo(2);
  }
}
