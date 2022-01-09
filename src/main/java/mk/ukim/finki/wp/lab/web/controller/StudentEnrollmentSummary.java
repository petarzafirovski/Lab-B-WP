//package mk.ukim.finki.wp.lab.web.controller;
//
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.service.CourseService;
//import mk.ukim.finki.wp.lab.service.StudentService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.thymeleaf.context.WebContext;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@Controller
//@RequestMapping("/studentEnrollment")
//public class StudentEnrollmentSummary {
//    private final StudentService studentService;
//    private final CourseService courseService;
//
//    public StudentEnrollmentSummary(StudentService studentService, CourseService courseService) {
//        this.studentService = studentService;
//        this.courseService = courseService;
//    }
//
//    @GetMapping
//    public String getStudentsEnrolled(Model  model,HttpServletRequest req){
//        String id = (String) req.getSession().getAttribute("courseId");
//        List<Student> studentList=this.courseService.findById(Long.parseLong(id)).get().getStudents();
//        req.getSession().setAttribute("students",studentList);
//        if(req.getSession().getAttribute("alreadyInCourse") != null){
//            boolean isPresent = (boolean) req.getSession().getAttribute("alreadyInCourse");
//            req.getSession().setAttribute("isPresent",isPresent);
//        }
//        model.addAttribute("bodyContent","studentsInCourse");
//        return "master-template";
//    }
//}
