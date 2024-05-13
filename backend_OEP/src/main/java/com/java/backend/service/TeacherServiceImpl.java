package com.java.backend.service;

import com.java.backend.model.Teacher;
import com.java.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Teacher registerTeacher(Teacher teacher) {
        String hashedPassword = passwordEncoder.encode(teacher.getTeacherPassword());
        teacher.setTeacherPassword(hashedPassword);

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher loginTeacher(String email, String password) {
        Teacher teacher = teacherRepository.findByTeacherEmail(email);
        if (teacher != null && passwordEncoder.matches(password, teacher.getTeacherPassword())) {
            return teacher;
        }
        return null;
    }
}
