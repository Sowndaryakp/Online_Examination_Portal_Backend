package com.java.backend.service;

import com.java.backend.model.Student;
import com.java.backend.repository.StudentRepository;
import com.java.backend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, BCryptPasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Student registerStudent(Student student) {
        // Set registration status to "pending" by default
        student.setRegistrationStatus("pending");
        String hashedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(hashedPassword);
        // Check if phone number is provided, if not, set it to an empty string or handle it as per your application logic
        if (student.getPhoneNumber() == null || student.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        return studentRepository.save(student);
    }

    @Override
    public Student loginStudent(String email, String password) {
        // Find student by email
        Student student = studentRepository.findByEmail(email);
        if (student != null) {
            // Check if student is approved
            if ("registered".equals(student.getRegistrationStatus())) {
                // Compare hashed passwords
                if (passwordEncoder.matches(password, student.getPassword())) {
                    // Generate JWT token
                    String token = jwtTokenUtil.generateToken(student);

                    // Create a map to hold response data (you can use any other way to represent the response)
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token);
                    response.put("approved", true);

                    // Return the response
                    return student;
                }
            } else {
                return null; // Return null if student is not approved
            }
        }
        return null; // Return null if email or password doesn't match
    }

    @Override
    public Student getStudentById(Long id) {
        // Implement logic to retrieve a student by ID from the repository
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        // Implement logic to update a student's information by ID in the repository
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(student.getName());
            existingStudent.setUsn(student.getUsn());
            existingStudent.setBranch(student.getBranch());
            existingStudent.setSemester(student.getSemester());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setPhoneNumber(student.getPhoneNumber());
            // Hash the password before setting it
            String hashedPassword = passwordEncoder.encode(student.getPassword());
            existingStudent.setPassword(hashedPassword);
            return studentRepository.save(existingStudent);
        } else {
            return null; // Return null if the student with the given ID does not exist
        }
    }

    @Override
    public boolean deleteStudent(Long id) {
        // Implement logic to delete a student by ID from the repository
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true; // Return true if the student was successfully deleted
        } else {
            return false; // Return false if the student with the given ID does not exist
        }
    }

    @Override
    public boolean approveStudentRegistration(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            student.setRegistrationStatus("approved");
            studentRepository.save(student);
            return true;
        }
        return false;
    }
}