package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.Type;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {


    public List<Course> findAllCourses(){
        return DataHolder.courses;
    }

    public Course findById (Long courseId){
        if(courseId != null) {
            return  DataHolder.courses.stream().filter(r -> r.getCourseId().equals(courseId)).findFirst().get();
            //casting with (Course) because we need an object
        }
        return null;

    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        Course course = findById(courseId);
        return course.getStudents();
    }

    public Course addStudentToCourse(Optional<Student> student, Optional<Course> course){
        if(student != null && course != null){
            Student student1 = student.get();
           course.get().getStudents().add(student1);
        }
        return null;
    }

    public Optional<Course> save (String name, String description, Teacher teacher, Type type){
       Optional<Course> doesExist= DataHolder.courses.stream().filter(r->r.getName().equals(name)).findFirst();
        if(!doesExist.isPresent()){
            DataHolder.courses.removeIf(r->r.getName().equals(name));

            Course course = new Course(name,description,teacher,type);

            DataHolder.courses.add(course);
            return Optional.of(course);
        }
        return  Optional.empty();
    }

    public void deleteById(Long id){
        DataHolder.courses.removeIf(r->r.getCourseId().equals(id));
    }

    public List<Course> listAll(){
        return DataHolder.courses.stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

//    public void removeDuplicate(Long id){
//        DataHolder.courses.removeIf(r->r.getCourseId().equals(id));
//    }



}
