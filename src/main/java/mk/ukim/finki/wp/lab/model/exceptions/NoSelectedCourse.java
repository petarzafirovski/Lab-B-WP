package mk.ukim.finki.wp.lab.model.exceptions;

public class NoSelectedCourse extends RuntimeException{
    public NoSelectedCourse(){
        super("No Selected course, please select one");
    }
}
