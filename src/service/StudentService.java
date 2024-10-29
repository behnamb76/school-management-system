package service;

import model.Student;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long studentId);
    Optional<Student> findStudentById(Long studentId);
    Set<Student> getAllStudents();
    int getCountOfStudents();
    List<Student> getStudentsByName(String firstName);
}
