package com.university.university.faculty;

import com.university.university.exception.FacultyNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findByIsDeletedFalse();
    }

    public Faculty getFacultyById(Integer id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found with id " + id));
    }

    public Faculty updateFaculty(Integer id, Faculty updatedFaculty) {
        Faculty faculty = getFacultyById(id);
        faculty.setFacultyName(updatedFaculty.getFacultyName());
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Integer id) {
        Faculty faculty = getFacultyById(id);
        faculty.setIsDeleted(true);
        facultyRepository.save(faculty);
    }
}
