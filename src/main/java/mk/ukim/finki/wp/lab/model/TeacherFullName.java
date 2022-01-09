package mk.ukim.finki.wp.lab.model;

import java.io.Serializable;

public class TeacherFullName implements Serializable {
    private String name;
    private String surname;

    public TeacherFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public TeacherFullName(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String fullName(){
        return this.name+" "+this.surname;
    }
}
