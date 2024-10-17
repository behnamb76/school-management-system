package repository;

import exception.NotFoundException;
import model.Course;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface CourseRepository {
    Set<Course> getAllCourse() throws SQLException;
    void addCourse(Course course) throws SQLException;
    void updateCourse(Course course) throws SQLException;
    void deleteCourse(long courseId) throws SQLException, NotFoundException;
    Optional<Course> findById(long courseId) throws SQLException;
}
