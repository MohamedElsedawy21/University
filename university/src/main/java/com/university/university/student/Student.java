package com.university.university.student;

import com.university.university.faculty.Faculty;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted=false;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;


    public Student() {
    }


    public Student(String studentName, Faculty faculty, Integer level) {
        this.studentName = studentName;
        this.faculty = faculty;
        this.level = level;
        this.isDeleted=false;

    }


    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public void setIsDeleted(boolean isDeleted){
        this.isDeleted=isDeleted;
    }
    public boolean getIsDeleted(){
        return isDeleted;
    }
}
