package repository;

import model.Exam;
import model.dto.ScoreReport;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface ExamRepository extends BaseRepository<Exam> {
    Set<ScoreReport> getScoresReport(String nationalCode) throws SQLException;

    Optional<Exam> findByIdAndCourseId(Long examId, Long courseId) throws SQLException;
}
