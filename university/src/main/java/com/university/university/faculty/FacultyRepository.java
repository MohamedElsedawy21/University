package com.university.university.faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    List<Faculty> findByIsDeletedFalse();
}
