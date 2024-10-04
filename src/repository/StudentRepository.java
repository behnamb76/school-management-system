package repository;

import model.Student;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentRepository {
    //language=SQL
    private static final String GET_ALL_STUDENTS_QUERY = "SELECT * FROM students";
    //language=SQL
    private static final String GET_COUNT_OF_STUDENTS = "SELECT count(*) FROM students";

    private static final String GET_STUDENTS_BY_NAME_QUERY = "SELECT * FROM students WHERE first_name = ?";

    private Database database = new Database();

    public Set<Student> getAllStudents() throws SQLException {
        ResultSet studentsResult = database.getSQLStatement().executeQuery(GET_ALL_STUDENTS_QUERY);
        Set<Student> students = new HashSet<>();
        while (studentsResult.next()) {
            Student student = new Student(
                    studentsResult.getLong("student_id"),
                    studentsResult.getString("first_name"),
                    studentsResult.getString("last_name"),
                    studentsResult.getDate("dob"),
                    studentsResult.getString("national_code"),
                    studentsResult.getDouble("gpu")
            );
            students.add(student);
        }
        return students;
    }

    public int getCountOfStudents() throws SQLException {
        ResultSet countResult = database.getSQLStatement().executeQuery(GET_COUNT_OF_STUDENTS);
        int studentCount = 0;
        while (countResult.next()) {
            studentCount = countResult.getInt("count");
        }
        return studentCount;
    }

    public List<Student> getStudentsByName(String firstName) throws SQLException {
        List<Student> students = new ArrayList<>();
        PreparedStatement smt = database.getDatabaseConnection().prepareStatement(GET_STUDENTS_BY_NAME_QUERY);
        smt.setString(1, firstName);
        ResultSet rs = smt.executeQuery();
        while (rs.next()) {
            Student student = new Student(
                    rs.getLong("student_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("dob"),
                    rs.getString("national_code"),
                    rs.getDouble("gpu")
            );
            students.add(student);
        }
        return students;
    }
}
