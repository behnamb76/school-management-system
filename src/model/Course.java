package model;

public class Course {
    private long courseId;
    private String title;
    private int unit;

    public Course() {
    }

    public Course(long courseId, String title, int unit) {
        this.courseId = courseId;
        this.title = title;
        this.unit = unit;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
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
