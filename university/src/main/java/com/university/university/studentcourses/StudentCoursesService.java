package com.university.university.studentcourses;

import org.springframework.stereotype.Service;
import com.university.university.exception.StudentCourseNotFoundException;
import java.util.*;

@Service
public class StudentCoursesService {
    private final StudentCoursesRepository studentCoursesRepository;

    public StudentCoursesService(StudentCoursesRepository studentCoursesRepository) {
        this.studentCoursesRepository = studentCoursesRepository;
    }

    public StudentCourses addStudentCourse(StudentCourses studentCourse) {
        return studentCoursesRepository.save(studentCourse);
    }

    public List<StudentCourses> getAllStudentCourses() {
        return studentCoursesRepository.findByIsDeletedFalse();
    }

    public StudentCourses getStudentCourseById(Integer id) {
        return studentCoursesRepository.findById(id)
                .orElseThrow(() -> new StudentCourseNotFoundException(
                        "Student course not found with id " + id
                ));
    }

    public StudentCourses updateStudentCourse(Integer id, StudentCourses updatedStudentCourse) {
        StudentCourses studentCourse = getStudentCourseById(id);
        studentCourse.setStudent(updatedStudentCourse.getStudent());
        studentCourse.setCourse(updatedStudentCourse.getCourse());
        studentCourse.setGrade(updatedStudentCourse.getGrade());
        studentCourse.setDeleted(updatedStudentCourse.isDeleted());
        return studentCoursesRepository.save(studentCourse);
    }

    public void deleteStudentCourse(Integer id) {
        StudentCourses studentCourse = getStudentCourseById(id);
        studentCourse.setDeleted(true);
        studentCoursesRepository.save(studentCourse);
    }
}
