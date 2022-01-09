package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/grades/{courseId}")
public class GradesController {
    private final CourseService courseService;
    private final GradeService gradeService;

    public GradesController(CourseService courseService, GradeService gradeService) {
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String listGrades(@PathVariable Long courseId, Model model){
        List<Grade> gradeList = this.gradeService.listGrades().stream().filter(r->r.getCourse().getCourseId().equals(courseId)).toList();
        model.addAttribute("grades",gradeList);
        Course course =this.courseService.findById(courseId).get();
        model.addAttribute("courseName",course.getCourseName());
        model.addAttribute("courseId",courseId);
        model.addAttribute("bodyContent","listGrades");
        return "master-template";
    }

    @GetMapping("/add")
    public String addGrade(@PathVariable String courseId, Model model){
        List<Student> list = courseService.findById(Long.parseLong(courseId)).get().getStudents();
        model.addAttribute("students",list);
        model.addAttribute("courseId",courseId);
        model.addAttribute("bodyContent","add-grade");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveGrade(@RequestParam String grade, @RequestParam String studentUsername, @RequestParam String time, HttpServletRequest request, @PathVariable String courseId)
    {
        this.gradeService.save(grade,studentUsername,courseId,time);
        return "redirect:/grades/{courseId}";
    }

    @PostMapping("/filtered")
    public String saveGrade(@PathVariable String courseId, @RequestParam String from,
                               @RequestParam String to,Model model){
        List<Grade> gradeList = this.gradeService.findGradeBetween(courseId,from,to);
        if(gradeList.size()==0){
            model.addAttribute("isEmpty", true);
            String errorMsg="Grade list is empty for the given filtering interval.";
            model.addAttribute("errorMsg",errorMsg);
            model.addAttribute("from",from);
            model.addAttribute("to",to);
            model.addAttribute("bodyContent","filteredGrades");
            return "master-template";
        }
        model.addAttribute("grades",gradeList);
        model.addAttribute("from",from);
        model.addAttribute("to",to);
        model.addAttribute("bodyContent","filteredGrades");
        return "master-template";
    }
}
