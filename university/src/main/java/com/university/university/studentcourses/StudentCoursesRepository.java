package com.university.university.studentcourses;

import com.university.university.studentcourses.StudentCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface StudentCoursesRepository extends JpaRepository<StudentCourses, Integer> {
    List<StudentCourses> findByIsDeletedFalse();
}
