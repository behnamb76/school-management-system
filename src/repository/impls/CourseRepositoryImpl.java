package repository.impls;

import exception.NotFoundException;
import model.Course;
import repository.CourseRepository;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CourseRepositoryImpl implements CourseRepository {
    private static final String GET_ALL_COURSES_QUERY = "SELECT * FROM courses";
    private static final String ADD_COURSE_QUERY = "INSERT INTO courses(course_title, course_unit) VALUES(?,?)";
    private static final String UPDATE_COURSE_QUERY = "UPDATE courses SET course_title = ?, course_unit = ? WHERE course_id = ?";
    private static final String DELETE_COURSE_FROM_COURSES_STUDENTS_QUERY = "DELETE FROM courses_students WHERE course_id = ?";
    private static final String DELETE_COURSE_QUERY = "DELETE FROM courses WHERE course_id = ?";
    private static final String FIND_COURSE_BY_ID = "SELECT * FROM courses WHERE course_id = ?";

    @Override
    public Set<Course> findAll() throws SQLException {
        ResultSet coursesResult = Database.getSQLStatement().executeQuery(GET_ALL_COURSES_QUERY);
        Set<Course> courses = new HashSet<>();
        while (coursesResult.next()) {
            Course course = new Course(
                    coursesResult.getLong("course_id"),
                    coursesResult.getString("course_title"),
                    coursesResult.getInt("course_unit")
            );
            courses.add(course);
        }
        return courses;
    }

    @Override
    public void add(Course course) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(ADD_COURSE_QUERY);
        preparedStatement.setString(1, course.getTitle());
        preparedStatement.setInt(2, course.getUnit());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Course course) throws SQLException {
        PreparedStatement preparedStatement = Database.getPreparedStatement(UPDATE_COURSE_QUERY);
        preparedStatement.setString(1, course.getTitle());
        preparedStatement.setInt(2, course.getUnit());
        preparedStatement.setLong(3, course.getCourseId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long courseId) throws SQLException, NotFoundException {
        if (this.findById(courseId).isPresent()) {
            PreparedStatement preparedStatement = Database.getPreparedStatement(DELETE_COURSE_FROM_COURSES_STUDENTS_QUERY);
            preparedStatement.setLong(1, courseId);
            preparedStatement.executeUpdate();

            preparedStatement = Database.getPreparedStatement(DELETE_COURSE_QUERY);
            preparedStatement.setLong(1, courseId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("Course with id of ".concat(String.valueOf(courseId)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Course> findById(Long courseId) throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(FIND_COURSE_BY_ID);
        ps.setLong(1, courseId);
        ResultSet rs = ps.executeQuery();
        Optional<Course> optionalCourse = Optional.empty();
        while (rs.next()) {
            Course course = new Course();
            course.setCourseId(rs.getLong("course_id"));
            course.setTitle(rs.getString("course_title"));
            course.setUnit(rs.getInt("course_unit"));
            optionalCourse = Optional.of(course);
        }
        return optionalCourse;
    }
}
