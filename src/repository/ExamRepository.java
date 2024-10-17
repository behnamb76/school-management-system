package repository;

import exception.NotFoundException;
import model.Exam;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface ExamRepository {
    Set<Exam> getAllExams() throws Exception;
    Exam getExamById(long examId) throws Exception;
    void addExam(Exam exam) throws Exception;
    void updateExam(Exam exam)  throws Exception;
    void deleteExam(long examId) throws Exception, NotFoundException;
    Optional<Exam> findById(Long examId) throws SQLException;
}
