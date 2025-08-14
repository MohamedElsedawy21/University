package com.university.university.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByIsDeletedFalse();
}
