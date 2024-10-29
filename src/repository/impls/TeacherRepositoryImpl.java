package repository.impls;

import exception.NotFoundException;
import model.Role;
import model.Teacher;
import model.User;
import repository.TeacherRepository;
import util.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TeacherRepositoryImpl implements TeacherRepository {
    private static final String GET_ALL_TEACHERS_QUERY = "SELECT * FROM teachers";
    private static final String ADD_TEACHER_QUERY = "INSERT INTO teachers(first_name, last_name, dob, national_code) VALUES(?,?,?,?)";
    private static final String UPDATE_TEACHER_QUERY = "UPDATE teachers SET first_name = ?, last_name = ?, dob = ?, national_code = ? WHERE teacher_id = ?";
    private static final String DELETE_TEACHER_QUERY = "DELETE FROM teachers WHERE teacher_id = ?";
    private static final String FIND_TEACHER_BY_ID = "SELECT * FROM teachers WHERE teacher_id = ?";
    private static final String FIND_TEACHER_BY_NATIONAL_CODE = "SELECT * FROM teachers WHERE national_code = ?";
    private final UserRepositoryImpl userRepository = new UserRepositoryImpl();

    @Override
    public Set<Teacher> findAll() throws SQLException {
        ResultSet teachersResult = Database.getSQLStatement().executeQuery(GET_ALL_TEACHERS_QUERY);
        Set<Teacher> teachers = new HashSet<>();
        while (teachersResult.next()) {
            String nc = teachersResult.getString("national_code");
            Teacher teacher = new Teacher(
                    teachersResult.getLong("teacher_id"),
                    teachersResult.getString("first_name"),
                    teachersResult.getString("last_name"),
                    teachersResult.getDate("dob"),
                    teachersResult.getString("national_code"),
                    teachersResult.getLong("course_id"),
                    userRepository.findByNationalCode(nc).get()
            );
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public void add(Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(ADD_TEACHER_QUERY);
        preparedStatement.setString(1, teacher.getFirstName());
        preparedStatement.setString(2, teacher.getLastName());
        preparedStatement.setDate(3, teacher.getDob());
        preparedStatement.setString(4, teacher.getNationalCode());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(UPDATE_TEACHER_QUERY);
        preparedStatement.setString(1, teacher.getFirstName());
        preparedStatement.setString(2, teacher.getLastName());
        preparedStatement.setDate(3, teacher.getDob());
        preparedStatement.setString(4, teacher.getNationalCode());
        preparedStatement.setLong(5, teacher.getTeacherId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long teacherId) throws SQLException, NotFoundException {
        if (this.findById(teacherId).isPresent()) {
            PreparedStatement preparedStatement = Database.getPreparedStatement(DELETE_TEACHER_QUERY);
            preparedStatement.setLong(1, teacherId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("Teacher with id of ".concat(String.valueOf(teacherId)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Teacher> findById(Long teacherId) throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(FIND_TEACHER_BY_ID);
        ps.setLong(1, teacherId);
        ResultSet rs = ps.executeQuery();
        Optional<Teacher> optionalTeacher = Optional.empty();
        while (rs.next()) {
            Teacher teacher =  new Teacher();
            teacher.setTeacherId(rs.getLong("teacher_id"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setDob(rs.getDate("dob"));
            teacher.setNationalCode(rs.getString("national_code"));
            teacher.setCourseId(rs.getLong("course_id"));
            optionalTeacher = Optional.of(teacher);
        }
        return optionalTeacher;
    }

    @Override
    public Optional<Teacher> findByNationalCode(String nationalCode) throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(FIND_TEACHER_BY_NATIONAL_CODE);
        ps.setString(1, nationalCode);
        ResultSet rs = ps.executeQuery();
        Optional<Teacher> optionalTeacher = Optional.empty();
        while (rs.next()) {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(rs.getLong("teacher_id"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setDob(rs.getDate("dob"));
            teacher.setNationalCode(rs.getString("national_code"));
            teacher.setCourseId(rs.getLong("course_id"));
            optionalTeacher = Optional.of(teacher);
        }
        return optionalTeacher;
    }
}
