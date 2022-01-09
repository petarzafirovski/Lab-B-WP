package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.TeacherFullName;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {

    public List<Teacher> findAll(){
        return DataHolder.teachers;
    }
    public Optional<Teacher> findById(Long id){
        return DataHolder.teachers.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public Optional<Teacher> save (String name, String surname,String date){
        Optional<Teacher> doesExist= DataHolder.teachers.stream().filter(r->r.getTeacherFirstName().equals(name)).findFirst();
        if(!doesExist.isPresent()){
            DataHolder.teachers.removeIf(r->r.getTeacherFirstName().equals(name));

            TeacherFullName teacherFullName = new TeacherFullName(name,surname);
           Teacher teacher = new Teacher(teacherFullName, LocalDate.parse(date));

            DataHolder.teachers.add(teacher);
            return Optional.of(teacher);
        }
        return  Optional.empty();
        }

    public void deleteById(Long id){
        DataHolder.teachers.removeIf(r->r.getId().equals(id));
    }

    public void removeDuplicate(Long id){
        DataHolder.teachers.removeIf(r->r.getId().equals(id));
    }

    }


