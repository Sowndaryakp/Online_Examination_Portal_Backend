package com.java.backend.service;

import com.java.backend.model.Student;

public interface StudentService {
    Student registerStudent(Student student);
    Student loginStudent(String email, String password);

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    boolean deleteStudent(Long id);

    boolean approveStudentRegistration(Long studentId);
}

