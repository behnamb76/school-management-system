package repository;

import exception.NotFoundException;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface TeacherRepository {
    Set<Teacher> getAllTeachers() throws SQLException;
    Teacher getTeacherById(long teacherId) throws SQLException;
    void addTeacher(Teacher teacher) throws SQLException;
    void updateTeacher(Teacher teacher) throws SQLException;
    void deleteTeacher(long teacherId) throws SQLException, NotFoundException;
    Optional<Teacher> findById(Long teacherId) throws SQLException;
}
