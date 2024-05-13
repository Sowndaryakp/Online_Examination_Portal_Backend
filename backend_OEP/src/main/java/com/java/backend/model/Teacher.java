package com.java.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(nullable = false)
    private String teacherName;

    @Column(nullable = false, unique = true)
    private String teacherEmail;

    @Column(nullable = false)
    private String teacherPassword;

    @Column(nullable = false)
    private String teacherSubject;

    @Column(nullable = false)
    private int teacherSem;

    public Teacher() {
    }

    public Teacher(String teacherName, String teacherEmail, String teacherPassword, String teacherSubject, int teacherSem) {
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
        this.teacherSubject = teacherSubject;
        this.teacherSem = teacherSem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public int getTeacherSem() {
        return teacherSem;
    }

    public void setTeacherSem(int teacherSem) {
        this.teacherSem = teacherSem;
    }
}
