package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/addStudent")
public class StudentsController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    public StudentsController(StudentService studentService, CourseService courseService, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String getStudents(Model model, HttpServletRequest req){
        String id = (String) req.getSession().getAttribute("selectedCourse");
        model.addAttribute("students",studentService.listAll());
        model.addAttribute("bodyContent","listStudents");
        return "master-template";
    }

    @PostMapping
    public String saveStudentInCourse(@RequestParam String studentUsername, Model model, HttpServletRequest req){
        String strId = (req.getParameter("id"));
        Long id=0L;
        try{
            id = Long.parseLong(strId);
        }catch (Exception exception){
            return "redirect:/listCourses";
        }

//        String username = req.getParameter("studentUsername");
        Course course = courseService.findById(id).get();

        var d = course.getStudents().stream().filter(r->r.getUsername().equals(studentUsername));
        if(d.findFirst().isPresent()){
            req.getSession().setAttribute("alreadyInCourse",true);
        }
        courseService.addStudentInCourse(studentUsername,id);
        req.getSession().setAttribute("courseName",course.getName());
        req.getSession().setAttribute("allStudents",course.getStudents());
        req.getSession().setAttribute("courseId",strId);
        return "redirect:/studentEnrollment";
    }
}
