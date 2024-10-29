package repository.impls;

import exception.NotFoundException;
import model.Exam;
import model.dto.ScoreReport;
import repository.ExamRepository;
import util.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ExamRepositoryImpl implements ExamRepository {
    private static final String GET_ALL_EXAMS_QUERY = "SELECT * FROM exams";
    private static final String ADD_EXAM_QUERY = "INSERT INTO exams(date, grade, student_id, course_id) VALUES(?,?,?,?)";
    private static final String UPDATE_EXAM_QUERY = "UPDATE exams SET date = ?, grade = ?, student_id = ?, course_id WHERE exam_id = ?";
    private static final String DELETE_EXAM_QUERY = "DELETE FROM exams WHERE exam_id = ?";
    private static final String FIND_EXAM_BY_ID = "SELECT * FROM exams WHERE exam_id = ?";
    private static final String FIND_EXAM_BY_ID_AND_COURSE_ID = "SELECT * FROM exams WHERE exam_id = ? and course_id = ?";
    private static final String FIND_EXAMS_OF_STUDENT = "select c.course_title, c.course_unit, e.grade from exams e join courses c on e.course_id = c.course_id join students s on e.student_id = s.student_id where national_code = ?;";

    @Override
    public Set<Exam> findAll() throws SQLException {
        ResultSet examsResult = Database.getSQLStatement().executeQuery(GET_ALL_EXAMS_QUERY);
        Set<Exam> exams = new HashSet<>();
        while (examsResult.next()) {
            Exam exam = new Exam(
                    examsResult.getLong("exam_id"),
                    examsResult.getDate("date"),
                    examsResult.getDouble("grade"),
                    examsResult.getLong("course_id"),
                    examsResult.getLong("course_id")
            );
            exams.add(exam);
        }
        return exams;
    }

    @Override
    public void add(Exam exam) throws SQLException{
        PreparedStatement preparedStatement = Database.getPreparedStatement(ADD_EXAM_QUERY);
        preparedStatement.setDate(1, exam.getExamDate());
        preparedStatement.setDouble(2, exam.getGrade());
        preparedStatement.setLong(3, exam.getStudentId());
        preparedStatement.setLong(4, exam.getCourseId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Exam exam) throws SQLException{
        PreparedStatement preparedStatement = Database.getPreparedStatement(UPDATE_EXAM_QUERY);
        preparedStatement.setDate(1, exam.getExamDate());
        preparedStatement.setDouble(2, exam.getGrade());
        preparedStatement.setLong(3, exam.getStudentId());
        preparedStatement.setLong(4, exam.getCourseId());
        preparedStatement.setLong(5, exam.getExamId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long examId) throws SQLException, NotFoundException{
        if (this.findById(examId).isPresent()) {
            PreparedStatement preparedStatement = Database.getPreparedStatement(DELETE_EXAM_QUERY);
            preparedStatement.setLong(1, examId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("Exam with id of ".concat(String.valueOf(examId)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Exam> findById(Long examId) throws SQLException{
        PreparedStatement ps = Database.getPreparedStatement(FIND_EXAM_BY_ID);
        ps.setLong(1, examId);
        ResultSet rs = ps.executeQuery();
        Optional<Exam> optionalExam = Optional.empty();
        while (rs.next()) {
            Exam exam =  new Exam();
            exam.setExamId(rs.getLong("exam_id"));
            exam.setExamDate(rs.getDate("exam_date"));
            exam.setGrade(rs.getDouble("grade"));
            exam.setStudentId(rs.getLong("student_id"));
            exam.setCourseId(rs.getLong("course_id"));
            optionalExam = Optional.of(exam);
        }
        return optionalExam;
    }

    @Override
    public Set<ScoreReport> getScoresReport(String nationalCode) throws SQLException{
        PreparedStatement ps = Database.getPreparedStatement(FIND_EXAMS_OF_STUDENT);
        ps.setString(1, nationalCode);
        ResultSet rs = ps.executeQuery();
        Set<ScoreReport> scores = new HashSet<>();
        while (rs.next()) {
            ScoreReport scoreReport = new ScoreReport();
            scoreReport.setCourseTitle(rs.getString("course_title"));
            scoreReport.setUnit(rs.getInt("course_unit"));
            scoreReport.setGrade(rs.getDouble("grade"));
            scores.add(scoreReport);
        }
        return scores;
    }

    @Override
    public Optional<Exam> findByIdAndCourseId(Long examId, Long courseId) throws SQLException {
        PreparedStatement ps = Database.getPreparedStatement(FIND_EXAM_BY_ID_AND_COURSE_ID);
        ps.setLong(1, examId);
        ps.setLong(2, courseId);
        ResultSet rs = ps.executeQuery();
        Optional<Exam> optionalExam = Optional.empty();
        while (rs.next()) {
            Exam exam =  new Exam();
            exam.setExamId(rs.getLong("exam_id"));
            exam.setExamDate(rs.getDate("exam_date"));
            exam.setGrade(rs.getDouble("grade"));
            optionalExam = Optional.of(exam);
        }
        return optionalExam;
    }
}
