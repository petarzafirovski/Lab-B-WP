package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.Type;
import mk.ukim.finki.wp.lab.model.exceptions.CourseAlreadyExistsWithNameException;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidCourseNameOrDescription;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidCourseType;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepo;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepo;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepo;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    public final CourseRepo courseRepo;
    public final StudentRepo studentRepo;
    public final TeacherRepo teacherRepo;

    public CourseServiceImpl(CourseRepo courseRepo, StudentRepo studentRepo, TeacherRepo teacherRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepo.findAllStudentsByCourseId(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        Student student = studentRepo.findAll()
                .stream()
                .filter(s -> s.getUsername().equals(username))
                .findFirst()
                .orElse(null);

           Course course= courseRepo.findById(courseId).orElseThrow(null);
          var d= course.getStudents().stream().filter(r->r.getUsername().equals(student.getUsername())).findAny();
          if(d.isEmpty()){
              course.getStudents().add(student);
          }

            return courseRepo.save(course);
    }

    @Override
    public List<Course> listAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course save(String name, String description, Long teacherId,String type) {
        if(teacherId==null || teacherId.equals(0L)){
            throw new TeacherNotFoundException(teacherId);
        }
        if(name==null || name.isEmpty() || description==null || description.isEmpty()){
            throw new InvalidCourseNameOrDescription();
        }

        if(type==null || type.isEmpty()){
            throw new InvalidCourseType();
        }

        Teacher teacher = this.teacherRepo.findById(teacherId).get();
       if(courseRepo.findAll().stream().filter(r->r.getName().equals(name)).collect(Collectors.toList()).size()>0){
           throw new CourseAlreadyExistsWithNameException(name);
       }

        Type course_type = switch (type.toUpperCase()) {
            case "WINTER" -> Type.WINTER;
            case "SUMMER" -> Type.SUMMER;
            case "MANDATORY" -> Type.MANDATORY;
            case "ELECTIVE" -> Type.ELECTIVE;
            default -> null;
        };

        return this.courseRepo.save(new Course(name,description,teacher,course_type));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.courseRepo.deleteById(id);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepo.findById(id);
    }

    @Override
    @Transactional
    public void updateExistingCourse(Long courseId, String name, String description, Long teacherId, String type) {
        Type course_type = switch (type.toUpperCase()) {
            case "WINTER" -> Type.WINTER;
            case "SUMMER" -> Type.SUMMER;
            case "MANDATORY" -> Type.MANDATORY;
            case "ELECTIVE" -> Type.ELECTIVE;
            default -> null;
        };
        this.courseRepo.updateCourse(courseId,name,description,teacherId,course_type);
    }


//    @Override
//    @Transactional
//    public void deleteByCourseId(Long id){
//        courseRepo.deleteByCourseId(id);
//    }

}
