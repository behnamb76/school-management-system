package repository.impls;

import exception.NotFoundException;
import model.Student;
import repository.StudentRepository;
import util.ApplicationContext;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentRepositoryImpl implements StudentRepository {
    private static final String GET_ALL_STUDENTS_QUERY = "SELECT * FROM students";
    //language=SQL
    private static final String GET_COUNT_OF_STUDENTS = "SELECT count(*) FROM students";

    private static final String GET_STUDENTS_BY_NAME_QUERY = "SELECT * FROM students WHERE first_name = ?";
    private static final String ADD_STUDENT_QUERY = "INSERT INTO students(first_name, last_name, dob, national_code, gpu) VALUES(?,?,?,?,?)";
    private static final String UPDATE_STUDENT_QUERY = "UPDATE students SET first_name = ?, last_name = ?, dob = ?, national_code = ?, gpu = ? WHERE student_id = ?";
    private static final String DELETE_STUDENT_FROM_COURSES_STUDENTS_QUERY = "DELETE FROM courses_students WHERE student_id = ?";
    private static final String DELETE_STUDENT_QUERY = "DELETE FROM students WHERE student_id = ?";
    private static final String FIND_STUDENT_BY_ID = "SELECT * FROM students WHERE student_id = ?";

    private final Database database = ApplicationContext.getDatabase();

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

    @Override
    public void addStudent(Student student) throws SQLException {
        PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(ADD_STUDENT_QUERY);
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setDate(3, student.getDob());
        preparedStatement.setString(4, student.getNationalCode());
        preparedStatement.setDouble(5, student.getGpu());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(UPDATE_STUDENT_QUERY);
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());
        preparedStatement.setDate(3, student.getDob());
        preparedStatement.setString(4, student.getNationalCode());
        preparedStatement.setDouble(5, student.getGpu());
        preparedStatement.setLong(6, student.getStudentId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteStudent(long studentId) throws SQLException, NotFoundException {
        if (this.findById(studentId).isPresent()) {
            PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(DELETE_STUDENT_QUERY);
            preparedStatement.setLong(1, studentId);
            preparedStatement.executeUpdate();

            preparedStatement = database.getDatabaseConnection().prepareStatement(DELETE_STUDENT_FROM_COURSES_STUDENTS_QUERY);
            preparedStatement.setLong(1, studentId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("Student with id of ".concat(String.valueOf(studentId)).concat(" not found!"));
        }
    }

    public Optional<Student> findById(Long studentId) throws SQLException{
        PreparedStatement ps = database.getPreparedStatement(FIND_STUDENT_BY_ID);
        ps.setLong(1, studentId);
        ResultSet rs = ps.executeQuery();
        Optional<Student> optionalStudent = Optional.empty();
        while (rs.next()) {
            Student student =  new Student();
            student.setStudentId(rs.getLong("student_id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setDob(rs.getDate("dob"));
            student.setNationalCode(rs.getString("national_code"));
            student.setGpu(rs.getDouble("gpu"));
            optionalStudent = Optional.of(student);
        }
        return optionalStudent;
    }
}
