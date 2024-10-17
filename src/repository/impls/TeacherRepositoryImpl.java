package repository.impls;

import exception.NotFoundException;
import model.Student;
import model.Teacher;
import repository.TeacherRepository;
import util.ApplicationContext;
import util.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TeacherRepositoryImpl implements TeacherRepository {
    private final Database database = ApplicationContext.getDatabase();
    private static final String GET_ALL_TEACHERS_QUERY = "SELECT * FROM students";
    private static final String ADD_TEACHER_QUERY = "INSERT INTO teachers(first_name, last_name, dob, national_code) VALUES(?,?,?,?)";
    private static final String UPDATE_TEACHER_QUERY = "UPDATE teachers SET first_name = ?, last_name = ?, dob = ?, national_code = ? WHERE teacher_id = ?";
    private static final String DELETE_TEACHER_QUERY = "DELETE FROM teachers WHERE teacher_id = ?";
    private static final String FIND_TEACHER_BY_ID = "SELECT * FROM teachers WHERE teacher_id = ?";

    @Override
    public Set<Teacher> getAllTeachers() throws SQLException {
        ResultSet teachersResult = database.getSQLStatement().executeQuery(GET_ALL_TEACHERS_QUERY);
        Set<Teacher> teachers = new HashSet<>();
        while (teachersResult.next()) {
            Teacher teacher = new Teacher(
                    teachersResult.getLong("teacher_id"),
                    teachersResult.getString("first_name"),
                    teachersResult.getString("last_name"),
                    teachersResult.getDate("dob"),
                    teachersResult.getString("national_code")
            );
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public void addTeacher(Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(ADD_TEACHER_QUERY);
        preparedStatement.setString(1, teacher.getFirstName());
        preparedStatement.setString(2, teacher.getLastName());
        preparedStatement.setDate(3, new Date(teacher.getDob().getTime()));
        preparedStatement.setString(4, teacher.getNationalCode());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(UPDATE_TEACHER_QUERY);
        preparedStatement.setString(1, teacher.getFirstName());
        preparedStatement.setString(2, teacher.getLastName());
        preparedStatement.setDate(3, new Date(teacher.getDob().getTime()));
        preparedStatement.setString(4, teacher.getNationalCode());
        preparedStatement.setLong(5, teacher.getTeacherId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteTeacher(long teacherId) throws SQLException, NotFoundException {
        if (this.findById(teacherId).isPresent()) {
            PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(DELETE_TEACHER_QUERY);
            preparedStatement.setLong(1, teacherId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("Teacher with id of ".concat(String.valueOf(teacherId)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Teacher> findById(long teacherId) throws SQLException {
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(FIND_TEACHER_BY_ID);
        ps.setLong(1, teacherId);
        ResultSet rs = ps.executeQuery();
        Optional<Teacher> optionalStudent = Optional.empty();
        while (rs.next()) {
            Teacher teacher =  new Teacher();
            teacher.setTeacherId(rs.getLong("teacher_id"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setDob(rs.getDate("dob"));
            teacher.setNationalCode(rs.getString("national_code"));
            optionalStudent = Optional.of(teacher);
        }
        return optionalStudent;
    }
}
