package service;

import model.Course;

import java.util.Optional;
import java.util.Set;

public interface CourseService {
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Long courseId);
    Optional<Course> findCourseById(Long courseId);
    Set<Course> getAllCourses();
}
