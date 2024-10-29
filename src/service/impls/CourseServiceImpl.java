package service.impls;

import model.Course;
import repository.CourseRepository;
import repository.impls.CourseRepositoryImpl;
import service.CourseService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository = new CourseRepositoryImpl();

    @Override
    public void addCourse(Course course) {
        try {
            courseRepository.add(course);
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            courseRepository.update(course);
        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    @Override
    public void deleteCourse(Long courseId) {
        try {
            courseRepository.delete(courseId);
        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }

    @Override
    public Optional<Course> findCourseById(Long courseId) {
        try {
            return courseRepository.findById(courseId);
        } catch (SQLException e) {
            System.out.println("Error finding course: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Set<Course> getAllCourses() {
        try {
            return courseRepository.findAll();
        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
            return Set.of();
        }
    }
}
