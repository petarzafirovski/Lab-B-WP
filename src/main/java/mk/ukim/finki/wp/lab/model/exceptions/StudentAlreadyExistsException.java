package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentAlreadyExistsException extends RuntimeException{
    public StudentAlreadyExistsException(String name,String surname){
    super(String.format("Student with name: %s and surname: %s already exists",name,surname));
    }
}
