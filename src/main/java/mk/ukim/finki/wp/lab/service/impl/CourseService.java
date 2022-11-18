package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements mk.ukim.finki.wp.lab.service.CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseService(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if(courseId==null){
            throw new IllegalArgumentException();
        }
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(username==null || username.isEmpty() || courseId == null){
            throw new IllegalArgumentException();
        }
        if(courseRepository.findById(courseId) == null){
            return null;
        }
        Course c = courseRepository.findById(courseId);
        Student s = studentService.searchByNameOrSurname(username).get(0);
        courseRepository.addStudentToCourse(s,c);
        return c;

    }



}
