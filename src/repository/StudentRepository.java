package repository;

import model.Student;

import java.sql.SQLException;
import java.util.*;

public interface StudentRepository extends BaseRepository<Student> {
    int getCountOfStudents() throws SQLException;
    List<Student> getStudentsByName(String firstName) throws SQLException;
}
