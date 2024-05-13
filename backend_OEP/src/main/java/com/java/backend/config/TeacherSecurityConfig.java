package com.java.backend.config;

import com.java.backend.util.TeacherJwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class TeacherSecurityConfig {

    @Bean(name = "teacherPasswordEncoder")
    public BCryptPasswordEncoder teacherPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TeacherJwtTokenUtil teacherJwtTokenUtil() {
        return new TeacherJwtTokenUtil();
    }
}
