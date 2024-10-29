package service.impls;

import model.Exam;
import model.dto.ScoreReport;
import repository.ExamRepository;
import repository.impls.ExamRepositoryImpl;
import service.ExamService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository = new ExamRepositoryImpl();

    @Override
    public void addExam(Exam exam) {
        try {
            examRepository.add(exam);
        } catch (SQLException e) {
            System.out.println("Error adding exam: " + e.getMessage());
        }
    }

    @Override
    public void updateExam(Exam exam) {
        try {
            examRepository.update(exam);
        } catch (SQLException e) {
            System.out.println("Error updating exam: " + e.getMessage());
        }
    }

    @Override
    public void deleteExam(Long examId) {
        try {
            examRepository.delete(examId);
        } catch (SQLException e) {
            System.out.println("Error deleting exam: " + e.getMessage());
        }
    }

    @Override
    public Optional<Exam> findExamById(Long examId) {
        try {
            return examRepository.findById(examId);
        } catch (SQLException e) {
            System.out.println("Error finding exam: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Set<Exam> getAllExams() {
        try {
            return examRepository.findAll();
        } catch (SQLException e) {
            System.out.println("Error retrieving exams: " + e.getMessage());
            return Set.of();
        }
    }

    @Override
    public Set<ScoreReport> getScoresReport(String nationalCode) {
        try {
            return examRepository.getScoresReport(nationalCode);
        } catch (SQLException e) {
            System.out.println("Error retrieving scores report: " + e.getMessage());
            return Set.of();
        }
    }

    @Override
    public Optional<Exam> findExamByIdAndCourseId(Long examId, Long courseId) {
        try {
            return examRepository.findByIdAndCourseId(examId, courseId);
        } catch (SQLException e) {
            System.out.println("Error finding exam: " + e.getMessage());
            return Optional.empty();
        }
    }
}
