package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class CourseAlreadyExistsWithNameException extends RuntimeException{
    public CourseAlreadyExistsWithNameException(String name){
        super(String.format("Course with name: %s already exists",name));
    }
}
