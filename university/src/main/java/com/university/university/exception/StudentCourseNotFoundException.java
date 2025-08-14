package com.university.university.exception;

public class StudentCourseNotFoundException extends RuntimeException {
    public StudentCourseNotFoundException(String message) {
        super(message);
    }
}
