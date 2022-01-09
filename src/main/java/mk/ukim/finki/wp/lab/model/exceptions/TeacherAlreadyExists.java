package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class TeacherAlreadyExists extends RuntimeException{

    public TeacherAlreadyExists(){
        super("Teacher already exists");
    }
}
