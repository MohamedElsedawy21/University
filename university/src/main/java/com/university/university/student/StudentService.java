package com.university.university.student;

import com.university.university.exception.StudentNotFoundException;
import com.university.university.faculty.Faculty;
import com.university.university.faculty.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student addStudent(Student student) {
        Faculty faculty = facultyRepository.findById(student.getFaculty().getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id " + student.getFaculty().getFacultyId()));
        student.setFaculty(faculty);
        return studentRepository.save(student);
    }


    public List<Student> getAllStudents() {
        return studentRepository.findByIsDeletedFalse();
    }


    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }


    public Student updateStudent(Integer id, Student updatedStudent) {
        Student student = getStudentById(id);
        Faculty faculty = facultyRepository.findById(updatedStudent.getFaculty().getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id " + updatedStudent.getFaculty().getFacultyId()));
        student.setFaculty(faculty);
        student.setStudentName(updatedStudent.getStudentName());
        student.setLevel(updatedStudent.getLevel());
        student.setIsDeleted(updatedStudent.getIsDeleted());
        return studentRepository.save(student);
    }


    public void deleteStudent(Integer id) {
        Student student=studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
        student.setIsDeleted(true);
        studentRepository.save(student);
    }
}
