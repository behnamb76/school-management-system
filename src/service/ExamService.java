package service;

import model.Exam;
import model.dto.ScoreReport;

import java.util.Optional;
import java.util.Set;

public interface ExamService {
    void addExam(Exam exam);
    void updateExam(Exam exam);
    void deleteExam(Long examId);
    Optional<Exam> findExamById(Long examId);
    Set<Exam> getAllExams();
    Set<ScoreReport> getScoresReport(String nationalCode);

    Optional<Exam> findExamByIdAndCourseId(Long examId, Long courseId);
}
