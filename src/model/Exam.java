package model;

import java.sql.Date;

public class Exam {
    private Long examId;
    private Date examDate;
    private Double grade;
    private Long courseId;
    private Long studentId;

    public Exam() {
    }

    public Exam(Long examId, Date examDate, Double grade, Long courseId, Long studentId) {
        this.examId = examId;
        this.examDate = examDate;
        this.grade = grade;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", examDate=" + examDate +
                ", grade=" + grade +
                '}';
    }
}
