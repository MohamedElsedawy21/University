package com.university.university.studentcourses;

import com.university.university.course.Course;
import com.university.university.faculty.Faculty;
import com.university.university.student.Student;
import jakarta.persistence.*;

@Entity
@Table(name="student_courses")
public class StudentCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_course_id")
    private Integer studentCourseId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;


    public StudentCourses() {}

    public StudentCourses(Student student, Course course, Integer grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.isDeleted = false;
    }

    public Integer getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(Integer studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
