package com.java.backend.controller;

import com.java.backend.model.Teacher;
import com.java.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<String> registerTeacher(@RequestBody Teacher teacher) {
        try {
            teacherService.registerTeacher(teacher);
            return ResponseEntity.ok("Teacher registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while registering the teacher.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginTeacher(@RequestBody Teacher teacher) {
        Teacher authenticatedTeacher = teacherService.loginTeacher(teacher.getTeacherEmail(), teacher.getTeacherPassword());
        if (authenticatedTeacher != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
