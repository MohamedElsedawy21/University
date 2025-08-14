package com.university.university.studentcourses;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-courses")
public class StudentCoursesController {

    private final StudentCoursesService studentCoursesService;

    public StudentCoursesController(StudentCoursesService studentCoursesService) {
        this.studentCoursesService = studentCoursesService;
    }

    @PostMapping
    public StudentCourses createStudentCourse(@RequestBody StudentCourses studentCourse) {
        return studentCoursesService.addStudentCourse(studentCourse);
    }

    @GetMapping
    public List<StudentCourses> getAllStudentCourses() {
        return studentCoursesService.getAllStudentCourses();
    }

    @GetMapping("/{id}")
    public StudentCourses getStudentCourseById(@PathVariable Integer id) {
        return studentCoursesService.getStudentCourseById(id);
    }

    @PutMapping("/{id}")
    public StudentCourses updateStudentCourse(@PathVariable Integer id, @RequestBody StudentCourses studentCourse) {
        return studentCoursesService.updateStudentCourse(id, studentCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentCourse(@PathVariable Integer id) {
        studentCoursesService.deleteStudentCourse(id);
    }
}
