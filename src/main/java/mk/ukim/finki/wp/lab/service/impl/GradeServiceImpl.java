package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepo;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepo;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepo;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    public final GradeRepo gradeRepo;
    public final StudentRepo studentRepo;
    public final CourseRepo courseRepo;

    public GradeServiceImpl(GradeRepo gradeRepo, StudentRepo studentRepo, CourseRepo courseRepo) {
        this.gradeRepo = gradeRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Grade> listGrades() {
        return this.gradeRepo.findAll();
    }

    @Override
    public Grade save(String grade, String studentUsername, String courseId, String localDateTime) {
//        if(!grade.equals("") && !studentUsername.equals("") && !courseId.equals("") && !localDateTime.equals("")){
            Student student = this.studentRepo.findById(studentUsername).orElseThrow(() -> new StudentNotFoundException(studentUsername));
            Course course= this.courseRepo.findById(Long.parseLong(courseId)).orElseThrow(() -> new CourseNotFoundException(Long.parseLong(courseId)));
            Character studentGrade=grade.charAt(0);

            return this.gradeRepo.save(new Grade(studentGrade,student,course, LocalDateTime.parse(localDateTime)));
    }

    @Override
    public List<Grade> findGradeBetween(String courseId,String from, String to) {
            return this.gradeRepo.findGradeByTimestampBetween(LocalDateTime.parse(from),LocalDateTime.parse(to)).stream().filter(r->r.getCourse().getCourse_Id().equals(Long.parseLong(courseId))).collect(Collectors.toList());
    }


}
