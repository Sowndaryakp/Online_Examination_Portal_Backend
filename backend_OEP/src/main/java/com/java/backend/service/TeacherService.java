package com.java.backend.service;

import com.java.backend.model.Teacher;

public interface TeacherService {
    Teacher registerTeacher(Teacher teacher);
    Teacher loginTeacher(String email, String password);
}
