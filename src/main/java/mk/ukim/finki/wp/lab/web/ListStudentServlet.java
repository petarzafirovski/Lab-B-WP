package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ListStudentServlet",urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {

    private final StudentService studentService;
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseRepository courseRepository;

    public ListStudentServlet(StudentService studentService, CourseService courseService, SpringTemplateEngine springTemplateEngine, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
        this.courseRepository = courseRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String id = (String) req.getSession().getAttribute("selectedCourse");
        context.setVariable("students",studentService.listAll());
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("listStudents.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = (req.getParameter("id"));
        Long id;
        try{
             id = Long.parseLong(strId);
        }catch (Exception exception){
            resp.sendRedirect("/listCourses");
            return;
        }

        String username = req.getParameter("studentUsername");
        Course course = courseService.findById(id).get();
        courseService.addStudentInCourse(username,id);
        var d = course.getStudents().stream().filter(r->r.getUsername().equals(username));
        if(d.findAny().isPresent()){
            req.getSession().setAttribute("alreadyInCourse",true);
        }
        req.getSession().setAttribute("courseName",course.getName());
        req.getSession().setAttribute("allStudents",course.getStudents());
        req.getSession().setAttribute("courseId",strId);
        resp.sendRedirect("/studentEnrollmentSummary");

    }
}
