package model;

import java.util.Date;

public class Teacher {
    private long teacherId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String nationalCode;

    public Teacher() {
    }

    public Teacher(long teacherId, String firstName, String lastName, Date dob, String nationalCode) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
