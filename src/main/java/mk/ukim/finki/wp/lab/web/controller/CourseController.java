package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/","/courses"})
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCourses(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            if(!error.equals("Teacher with id: 0 was not found")){
                model.addAttribute("hasError",true);
                model.addAttribute("error",error);
            }

        }
        List<Course> courses = this.courseService.listAll();
        model.addAttribute("courses",courses);
        model.addAttribute("bodyContent","listCourses");
        return "master-template";
    }

    @PostMapping
    public String doPost(HttpServletRequest req) {
        String id = req.getParameter("selectedCourseRadio");
        req.getSession().setAttribute("selectedCourse",id);
        return "redirect:/addStudent";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long teacherId,@RequestParam String type, HttpServletRequest request){
        String id =request.getParameter("hiddenId");
        Optional<Course> course = this.courseService.findById(Long.parseLong(id));
        try{
            if(course.isPresent()){
                Long courseId=Long.parseLong(id);
                this.courseService.updateExistingCourse(courseId,name,description,teacherId,type);
            }else{
                this.courseService.save(name,description,teacherId,type);
            }

            return "redirect:/courses";
        }
        catch (RuntimeException exception){
            return "redirect:/courses?error=" + exception.getMessage();
        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCourse(@PathVariable Long id){
        this.courseService.deleteById(id);
        return "redirect:/listCourses";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddCoursePage(Model model){
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("add",true);
        model.addAttribute("bodyContent","add-course");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditCoursePage(@PathVariable Long id, Model model){
       if(courseService.findById(id).isPresent()) {
           model.addAttribute("edit",true);
           Optional<Course> course = this.courseService.findById(id);
           List<Teacher> teachers = this.teacherService.findAll();
           model.addAttribute("teachers", teachers);
           model.addAttribute("course",course);
           model.addAttribute("bodyContent","add-course");
           return "master-template";
       }else {
           model.addAttribute("hasError", true);
           model.addAttribute("error", "Course was not found");
           return "redirect:/courses?error=Invalid course id";
       }
    }


}
