package model;

import java.sql.Date;

public class Admin {
    private Long adminId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String nationalCode;
    private User user;

    public Admin() {
    }

    public Admin(Long adminId, String firstName, String lastName, Date dob, String nationalCode, User user) {
        this.adminId = adminId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.nationalCode = nationalCode;
        this.user = user;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
