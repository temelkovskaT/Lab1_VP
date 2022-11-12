package mk.ukim.finki.wp.lab.web.servlet;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="list-student-servlet", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {
    private final StudentService studentService;
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListStudentServlet(StudentService studentService, CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req,resp, req.getServletContext());
        context.setVariable("students", studentService.listAll());
        this.springTemplateEngine.process("listStudents.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String studentChosen = req.getParameter("student");
       req.getSession().setAttribute("studentChosen", studentChosen);
       Long courseId = Long.parseLong(req.getSession().getAttribute("courseChosen").toString());

       courseService.addStudentInCourse(studentChosen, courseId);
       resp.sendRedirect("/StudentEnrollmentSummary");

    }
}
