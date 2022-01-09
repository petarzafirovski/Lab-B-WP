package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.TeacherFullName;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherAlreadyExists;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepo;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepo teacherRepo;

    public TeacherServiceImpl(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepo.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepo.findById(id);
    }

    @Override
    public Teacher save(String name, String surname,String date) {
        if(teacherRepo.findAll().stream().anyMatch(r -> r.getTeacherFirstName().equals(name) && r.getTeacherSurname().equals(surname))){
            throw new TeacherAlreadyExists();
        }
        TeacherFullName teacherFullName = new TeacherFullName(name,surname);
        return this.teacherRepo.save(new Teacher(teacherFullName, LocalDate.parse(date)));
    }

    @Override
    public void deleteById(Long id) {
        this.teacherRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void updateExistingTeacher(Long teacherId, String name, String surname,String date) {
        Teacher teacher = this.teacherRepo.getById(teacherId);
        this.teacherRepo.updateTeacher(teacherId,teacher.getTeacherFullName(),LocalDate.parse(date));

    }

//    @Override
//    public void removeDuplicate(Long id) {
//        this.teacherRepo.removeDuplicate(id);
//    }
}
