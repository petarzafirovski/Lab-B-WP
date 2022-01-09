package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/getStudents")
public class GetStudentsInEachCourseController {
    private final CourseService courseService;

    public GetStudentsInEachCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getStudents(Model model){
        model.addAttribute("courses",this.courseService.listAll());
        model.addAttribute("bodyContent","listStudentsInCourse");
        return "master-template";
    }
}
