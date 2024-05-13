package com.java.backend.controller;

import com.java.backend.model.LoginRequest;
import com.java.backend.model.Student;
import com.java.backend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Your login logic here
        Student student = new Student();
        String token = jwtTokenUtil.generateToken(student); // Assuming you have a Student object
        // Construct your response with token, message, and approved status
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Login successful");
        response.put("approved", true); // Assuming the login is considered approved
        return ResponseEntity.ok(response);
    }

}

