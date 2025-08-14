package com.university.university.faculty;

import jakarta.persistence.*;

@Entity
@Table(name="faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="faculty_id")
    private Integer facultyId;

    @Column(name = "faculty_name", nullable = false, unique = true)
    private String facultyName;

    @Column(name="is_deleted",nullable = false)
    private boolean isDeleted;

    public Faculty() {}

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
        this.isDeleted = false;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
}
