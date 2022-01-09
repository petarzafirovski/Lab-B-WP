package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeService {
    List<Grade> listGrades();
    Grade save(String grade, String studentUsername, String courseId, String localDateTime);
    List<Grade> findGradeBetween(String courseId,String from, String to);
}
