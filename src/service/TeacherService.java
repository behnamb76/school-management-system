package service;

import model.Teacher;

import java.util.Optional;
import java.util.Set;

public interface TeacherService {
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(Long teacherId);
    Optional<Teacher> findTeacherById(Long teacherId);
    Set<Teacher> getAllTeachers();

    Optional<Teacher> findTeacherByNationalCode(String nationalCode);
}
