package repository.impls;

import exception.NotFoundException;
import model.Exam;
import model.Student;
import repository.ExamRepository;
import util.ApplicationContext;
import util.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ExamRepositoryImpl implements ExamRepository {
    private final Database database = ApplicationContext.getDatabase();
    private static final String GET_ALL_EXAMS_QUERY = "SELECT * FROM exams";
    private static final String ADD_EXAM_QUERY = "INSERT INTO exams(date, grade) VALUES(?,?)";
    private static final String UPDATE_EXAM_QUERY = "UPDATE exams SET date = ?, grade = ? WHERE exam_id = ?";
    private static final String DELETE_EXAM_QUERY = "DELETE FROM exams WHERE exam_id = ?";
    private static final String FIND_EXAM_BY_ID = "SELECT * FROM exams WHERE exam_id = ?";

    @Override
    public Set<Exam> getAllExams() throws SQLException {
        ResultSet examsResult = database.getSQLStatement().executeQuery(GET_ALL_EXAMS_QUERY);
        Set<Exam> exams = new HashSet<>();
        while (examsResult.next()) {
            Exam exam = new Exam(
                    examsResult.getLong("exam_id"),
                    examsResult.getDate("date"),
                    examsResult.getDouble("grade")
            );
            exams.add(exam);
        }
        return exams;
    }

    @Override
    public void addExam(Exam exam) throws SQLException{
        PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(ADD_EXAM_QUERY);
        preparedStatement.setDate(1, new Date(exam.getExamDate().getTime()));
        preparedStatement.setDouble(2, exam.getGrade());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateExam(Exam exam) throws SQLException{
        PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(UPDATE_EXAM_QUERY);
        preparedStatement.setDate(1, new Date(exam.getExamDate().getTime()));
        preparedStatement.setDouble(2, exam.getGrade());
        preparedStatement.setLong(3, exam.getExamId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteExam(long examId) throws SQLException, NotFoundException{
        if (this.findById(examId).isPresent()) {
            PreparedStatement preparedStatement = database.getDatabaseConnection().prepareStatement(DELETE_EXAM_QUERY);
            preparedStatement.setLong(1, examId);
            preparedStatement.executeUpdate();
        } else {
            throw new NotFoundException("Exam with id of ".concat(String.valueOf(examId)).concat(" not found!"));
        }
    }

    @Override
    public Optional<Exam> findById(long examId) throws SQLException{
        PreparedStatement ps = database.getDatabaseConnection().prepareStatement(FIND_EXAM_BY_ID);
        ps.setLong(1, examId);
        ResultSet rs = ps.executeQuery();
        Optional<Exam> optionalStudent = Optional.empty();
        while (rs.next()) {
            Exam exam =  new Exam();
            exam.setExamId(rs.getLong("exam_id"));
            exam.setExamDate(rs.getDate("exam_date"));
            exam.setGrade(rs.getDouble("grade"));
            optionalStudent = Optional.of(exam);
        }
        return optionalStudent;
    }
}
