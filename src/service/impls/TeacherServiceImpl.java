package service.impls;

import model.Admin;
import model.Teacher;
import model.User;
import repository.TeacherRepository;
import repository.UserRepository;
import repository.impls.TeacherRepositoryImpl;
import repository.impls.UserRepositoryImpl;
import service.TeacherService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository = new TeacherRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void addTeacher(Teacher teacher) {
        try {
            teacherRepository.add(teacher);
        } catch (SQLException e) {
            System.out.println("Error adding teacher: " + e.getMessage());
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        try {
            teacherRepository.update(teacher);
        } catch (SQLException e) {
            System.out.println("Error updating teacher: " + e.getMessage());
        }
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        try {
            teacherRepository.delete(teacherId);
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            if (teacher.isPresent()) {
                User user = teacher.get().getUser();
                userRepository.delete(user.getUserId());
            } else {
                System.out.println("User of Teacher not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting teacher: " + e.getMessage());
        }
    }

    @Override
    public Optional<Teacher> findTeacherById(Long teacherId) {
        try {
            return teacherRepository.findById(teacherId);
        } catch (SQLException e) {
            System.out.println("Error finding teacher: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Set<Teacher> getAllTeachers() {
        try {
            return teacherRepository.findAll();
        } catch (SQLException e) {
            System.out.println("Error retrieving teachers: " + e.getMessage());
            return Set.of();
        }
    }

    @Override
    public Optional<Teacher> findTeacherByNationalCode(String nationalCode) {
        try {
            return teacherRepository.findByNationalCode(nationalCode);
        } catch (SQLException e) {
            System.out.println("Error finding teacher: " + e.getMessage());
            return Optional.empty();
        }
    }
}
