package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.h2.engine.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getTeachers(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
       List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("bodyContent","listTeachers");
        return "master-template";
    }

    @GetMapping("/add-new")
    @PreAuthorize("hasRole('ADMIN')")
    public String getTeacherAddPage(Model model){
        model.addAttribute("add",true);
        model.addAttribute("bodyContent","add-teacher");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditCoursePage(@PathVariable Long id, Model model){
        if(teacherService.findAll().stream().anyMatch(r->r.getId().equals(id))) {
            Teacher teacher = this.teacherService.findById(id).get();
            model.addAttribute("teacher",teacher);
            model.addAttribute("edit",true);
            model.addAttribute("bodyContent","add-teacher");
            return "master-template";
        }else {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "Teacher was not found");
            return "redirect:/teachers?error=Invalid teacher id";
        }
    }

    @PostMapping("/add")
    public String saveTeacher(@RequestParam String name, @RequestParam String surname,@RequestParam String teacherId,@RequestParam String date ,HttpServletRequest request){
      String id =request.getParameter("teacherId");
        try{
            if(!id.equals("")){
                Long teacher_Id=Long.parseLong(teacherId);
                this.teacherService.updateExistingTeacher(teacher_Id,name,surname,date);
            }else {
                this.teacherService.save(name, surname,date);
            }
            return "redirect:/teachers";
        }
        catch (RuntimeException exception){
            return "redirect:/teachers?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTeacher(@PathVariable Long id){
        this.teacherService.deleteById(id);
        return "redirect:/teachers";
    }
}
