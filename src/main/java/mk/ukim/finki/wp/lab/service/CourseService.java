package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enums.Type;

import java.util.List;
import java.util.Optional;

public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course save(String name, String description, Long teacherId,String type);
    void deleteById(Long id);
    Optional<Course> findById(Long id);
    void updateExistingCourse(Long courseId, String name, String description, Long teacherId, String type);
}
