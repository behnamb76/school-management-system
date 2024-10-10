import model.Student;
import repository.StudentRepository;
import repository.impls.StudentRepositoryImpl;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        try {
            List<Student> studentList = studentRepository.getStudentsByName("Behnam");
            System.out.println(studentList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}