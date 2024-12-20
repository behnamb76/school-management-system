package model;

import java.sql.Date;

public class Student {
    private Long studentId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String nationalCode;
    private Double gpu;
    private User user;

    public Student() {
    }

    public Student(Long studentId, String firstName, String lastName, Date dob, String nationalCode, Double gpu, User user) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.gpu = gpu;
        this.user = user;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Double getGpu() {
        return gpu;
    }

    public void setGpu(Double gpu) {
        this.gpu = gpu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", nationalCode='" + nationalCode + '\'' +
                ", gpu=" + gpu +
                '}';
    }
}
