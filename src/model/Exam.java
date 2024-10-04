package model;

import java.util.Date;

public class Exam {
    private long examId;
    private Date examDate;
    private double grade;

    public Exam() {
    }

    public Exam(long examId, Date examDate, double grade) {
        this.examId = examId;
        this.examDate = examDate;
        this.grade = grade;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
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
