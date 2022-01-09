package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> students = new ArrayList<Student>();
    public static List<Course> courses = new ArrayList<Course>();
    public static List<Teacher> teachers = new ArrayList<Teacher>();

//    @PostConstruct
//    public void init(){
//        Teacher teacher = new Teacher("Riste","Stojanov");
//        Teacher teacher2 = new Teacher("Dimitar","Trajanov");
//        Teacher teacher3 = new Teacher("Saso","Gramatikov");
//        Teacher teacher4 = new Teacher("Kostadin","Misev");
//        Teacher teacher5 = new Teacher("Ana","Todorovska");
//
//        teachers.add(teacher);
//        teachers.add(teacher2);
//        teachers.add(teacher3);
//        teachers.add(teacher4);
//        teachers.add(teacher5);
//
//        courses.add(new Course("Veb programiranje","SpringBoot",teacher));
//        courses.add(new Course("Operativni sistemi","PuTTy",teacher2));
//        courses.add(new Course("Elektronska i mobilna trgovija","React.js",teacher3));
//        courses.add(new Course("Kopmjuterski mrezi","HTTP,TCP IP",teacher4));
//        courses.add(new Course("Internet programiranje","JavaScript",teacher5));
//
//        students.add(new Student("petar.zafirovski","pz123","Petar","Zafirovski"));
//        students.add(new Student("marko.markovski","marko12345","Marko","Markovski"));
//        students.add(new Student("stole.stojanovski","stole123","Stole","Stojanovski"));
//        students.add(new Student("marija.marijovska","marija1234","Marija","Marijovska"));
//        students.add(new Student("petko.petkovski","petko12","Petko","Petkovski"));
//
//
//
//    }


}
