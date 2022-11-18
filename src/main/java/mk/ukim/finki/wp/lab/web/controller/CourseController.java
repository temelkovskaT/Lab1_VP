package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller()
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        List<Course> courseList = this.courseService.listAll();
        model.addAttribute("courseList", courseList);
        return "listCourses";
    }

    @PostMapping
    public String chooseCourse(HttpServletRequest request, Model model){
        String courseChosen = request.getParameter("course");
        request.getSession().setAttribute("courseChosen", courseChosen);
        return "redirect:/addStudent";
    }
//
//    @PostMapping("/add")
//    public String saveCourse(@RequestParam String name,
//                             @RequestParam String description,
//                             @RequestParam Long teacherId){
//        this.courseService.save(name,)
//        return "redirect:/addStudent";
//    }

}
