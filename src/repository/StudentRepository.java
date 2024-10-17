package repository;

import exception.NotFoundException;
import model.Exam;
import model.Student;
import util.ApplicationContext;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public interface StudentRepository {
    Set<Student> getAllStudents() throws SQLException;
    int getCountOfStudents() throws SQLException;
    List<Student> getStudentsByName(String firstName) throws SQLException;
    void addStudent(Student student) throws Exception;
    void updateStudent(Student student)  throws Exception;
    void deleteStudent(long studentId) throws Exception, NotFoundException;
    Optional<Student> findById(Long studentId) throws SQLException;
}
