package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher save (String name, String surname,String date);
    void deleteById(Long id);
    void updateExistingTeacher(Long teacherId, String name, String surname,String date);
}
