package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.enums.Type;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description,Teacher teacher,Type type) {
        this.name = name;
        this.description = description;
        this.students= new ArrayList<>();
        this.teacher=teacher;
        this.type=type;
    }



    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course() {

    }
    public String getCourseName(){
        return this.name;
    }

    public Long getCourse_Id(){
        return this.courseId;
    }

    public String getCourseDescription(){
        return this.description;

    }

    public void setTeacherId(Long id){
        this.teacher.setId(id);
    }
}
