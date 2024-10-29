package model.dto;

public class ScoreReport {
    private String courseTitle;
    private Integer unit;
    private Double grade;

    public ScoreReport() {
    }

    public ScoreReport(String courseTitle, Integer unit, Double grade) {
        this.courseTitle = courseTitle;
        this.unit = unit;
        this.grade = grade;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ScoreReport{" +
                "courseTitle='" + courseTitle + '\'' +
                ", unit=" + unit +
                ", grade=" + grade +
                '}';
    }
}
