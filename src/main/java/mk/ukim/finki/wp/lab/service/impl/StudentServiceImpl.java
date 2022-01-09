package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.EmptyStudentUsernameOrSurname;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepo;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> listAll() {
        return this.studentRepo.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String name, String surname) {
        return this.studentRepo.findAllByNameOrSurname(name,surname);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }

        if(surname.isEmpty() || name.isEmpty()){
            throw new EmptyStudentUsernameOrSurname();
        }

        if(!this.studentRepo.findAllByNameOrSurname(name,surname).isEmpty()){
            throw new StudentAlreadyExistsException(name,surname);
        }

        Student student = new Student(username,password,name,surname);
        this.studentRepo.save(student);
        return student;

    }
}
