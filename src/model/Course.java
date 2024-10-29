package model;

public class Course {
    private Long courseId;
    private String title;
    private Integer unit;

    public Course() {
    }

    public Course(Long courseId, String title, Integer unit) {
        this.courseId = courseId;
        this.title = title;
        this.unit = unit;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", unit=" + unit +
                '}';
    }
}
