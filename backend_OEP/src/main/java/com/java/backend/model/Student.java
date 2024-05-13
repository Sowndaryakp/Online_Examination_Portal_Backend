package com.java.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String usn;

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false)
    private int semester;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "ph_no")
    private String phoneNumber;

    @Column(nullable = false, name = "registration_status")
    private String registrationStatus; // Values: pending, registered, rejected

    public Student() {
    }

    public Student(Long id, String name, String usn, String branch, int semester, String email, String password, String phoneNumber, String registrationStatus) {
        this.id = id;
        this.name = name;
        this.usn = usn;
        this.branch = branch;
        this.semester = semester;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.registrationStatus = registrationStatus;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    // Method to check if student is approved
    public boolean isApproved() {
        return registrationStatus.equals("approved");
    }
}
