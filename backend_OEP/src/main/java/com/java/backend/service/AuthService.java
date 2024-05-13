package com.java.backend.service;

import com.java.backend.model.Student;
import com.java.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private StudentRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String email, String password) {
        // Retrieve user by email
        Student student = userRepository.findByEmail(email);

        if (student != null) {
            // Compare hashed password
            return passwordEncoder.matches(password, student.getPassword());
        } else {
            // User with provided email not found
            return false;
        }
    }
}
