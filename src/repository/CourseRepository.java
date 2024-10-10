package repository;

import model.Course;

import java.sql.SQLException;
import java.util.Set;

public interface CourseRepository {
    Set<Course> getAllCourse() throws SQLException;
    Course getCourseById(long courseId) throws SQLException;
    void addCourse(Course course) throws SQLException;
    void updateCourse(Course course) throws SQLException;
    void deleteCourse(long courseId) throws SQLException;
}
