package service.impls;

import model.Admin;
import model.Student;
import model.User;
import repository.StudentRepository;
import repository.UserRepository;
import repository.impls.StudentRepositoryImpl;
import repository.impls.UserRepositoryImpl;
import service.StudentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository = new StudentRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void addStudent(Student student) {
        try {
            studentRepository.add(student);
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            studentRepository.update(student);
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    @Override
    public void deleteStudent(Long studentId) {
        try {
            studentRepository.delete(studentId);
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                User user = student.get().getUser();
                userRepository.delete(user.getUserId());
            } else {
                System.out.println("User of Admin not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    @Override
    public Optional<Student> findStudentById(Long studentId) {
        try {
            return studentRepository.findById(studentId);
        } catch (SQLException e) {
            System.out.println("Error finding student: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Set<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
            return Set.of();
        }
    }

    @Override
    public int getCountOfStudents() {
        try {
            return studentRepository.getCountOfStudents();
        } catch (SQLException e) {
            System.out.println("Error retrieving student count: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Student> getStudentsByName(String firstName) {
        try {
            return studentRepository.getStudentsByName(firstName);
        } catch (SQLException e) {
            System.out.println("Error retrieving students by name: " + e.getMessage());
            return List.of();
        }
    }
}
