package com.university.university.course;

import com.university.university.exception.CourseNotFoundException;
import com.university.university.faculty.Faculty;
import com.university.university.faculty.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, FacultyRepository facultyRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }
    public Course addCourse(Course course) {
        Faculty faculty = facultyRepository.findById(course.getFaculty().getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id " + course.getFaculty().getFacultyId()));
        course.setFaculty(faculty);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findByIsDeletedFalse();
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + id));
    }

    public Course updateCourse(Integer id, Course updatedCourse) {
        Course course = getCourseById(id);
        Faculty faculty = facultyRepository.findById(updatedCourse.getFaculty().getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id " + updatedCourse.getFaculty().getFacultyId()));
        course.setFaculty(faculty);
        course.setCourseName(updatedCourse.getCourseName());
        course.setDeleted(updatedCourse.isDeleted());
        return courseRepository.save(course);
    }

    public void deleteCourse(Integer id) {
        Course course = getCourseById(id);
        course.setDeleted(true);
        courseRepository.save(course);
    }
}

