package com.university.university.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByIsDeletedFalse();
}
