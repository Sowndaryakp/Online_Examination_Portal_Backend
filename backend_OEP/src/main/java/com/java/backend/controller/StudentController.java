package com.java.backend.controller;

import com.java.backend.model.AuthResponse;
import com.java.backend.model.Student;
import com.java.backend.service.StudentService;
import com.java.backend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        try {
            // Register the student
            studentService.registerStudent(student);
            return ResponseEntity.ok("Student registered successfully.");
        } catch (IllegalArgumentException e) {
            // Handle missing phone number error
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while registering the student.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody Student student) {
        Student authenticatedStudent = studentService.loginStudent(student.getEmail(), student.getPassword());
        if (authenticatedStudent != null) {
            String token = jwtTokenUtil.generateToken(authenticatedStudent);
            boolean approved = authenticatedStudent.isApproved();
            AuthResponse response = new AuthResponse("Login successful", token, approved); // Create AuthResponse object
            return ResponseEntity.ok(response); // Return response entity with AuthResponse object
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    // Method to generate token for the authenticated student
    private String generateTokenForStudent(Student student) {
        // Generate token using your token generation logic (e.g., using JwtTokenUtil)
        String token = jwtTokenUtil.generateToken(student);
        return token;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

}