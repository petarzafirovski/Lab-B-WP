package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String name;
//    private String surname;

    @Convert(converter = TeacherFullNameConverter.class)
      private TeacherFullName teacherFullName;

      @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;

    public Teacher(TeacherFullName teacherFullName, LocalDate dateEmployment) {
//        this.name = name;
//        this.surname = surname;
        this.teacherFullName = teacherFullName;
        this.dateOfEmployment= dateEmployment;
    }

    public Teacher() {

    }

    public TeacherFullName getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(TeacherFullName teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public String getTeacherFirstName(){
        return this.teacherFullName.getName();
    }

    public String getTeacherSurname(){
        return this.teacherFullName.getSurname();
    }

    public void setTeacherFirstName(String name){
        this.teacherFullName.setName(name);;
    }
    public void setTeacherSurname(String surname){
        this.teacherFullName.setName(surname);;
    }


    //    public String getFullNameTeacher(){
//        String fullName=this.name+" "+this.surname;
//        return fullName;
//    }
}
