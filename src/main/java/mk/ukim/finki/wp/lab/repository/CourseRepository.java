package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    private static List<Course> courses = new ArrayList<>();
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;


    private List<Student> studentsVP = new ArrayList<>();
    private List<Student> studentsBP = new ArrayList<>();
    private List<Student> studentsVNP = new ArrayList<>();
    private List<Student> studentsV = new ArrayList<>();
    private List<Student> studentsKE = new ArrayList<>();



    @PostConstruct
    public void init(){

        for(int i=1; i<=5; i++){
            studentsVP.add(new Student("username"+i, "pass"+i,
                    "student"+i, "surname"+i));
        }
        for(int i=4; i<=7; i++){
            studentsBP.add(new Student("username"+i, "pass"+i,
                    "student"+i, "surname"+i));
        }
        for(int i=8; i<=10; i++){
            studentsVNP.add(new Student("username"+i, "pass"+i,
                    "student"+i, "surname"+i));
        }
        for(int i=10; i<=15; i++){
            studentsV.add(new Student("username"+i, "pass"+i,
                    "student"+i, "surname"+i));
        }
        for(int i=16; i<=20; i++){
            studentsKE.add(new Student("username"+i, "pass"+i,
                    "student"+i, "surname"+i));
        }


        courses.add(new Course(1L, "Веб програмирање", "neshto1" ,studentsVP,teacherRepository.findAll().get(0)));
        courses.add(new Course(2L, "Бази на податоци", "neshto2",studentsBP,teacherRepository.findAll().get(1)));
        courses.add(new Course(3L, "Вовед во науката за податоци", "neshto3",studentsVNP, teacherRepository.findAll().get(2)));
        courses.add(new Course(4L, "Визуелизација", "neshto4",studentsV, teacherRepository.findAll().get(3)));
        courses.add(new Course(5L, "Компјутерска Етика", "neshto5",studentsKE, teacherRepository.findAll().get(4)));
    }

    public List<Course> findAllCourses(){
        return courses;
    }

    public Course findById(Long courseId){
        if(courseId == null){
            return null;
        }
        return courses.stream().filter(c->c.getCourseId().equals(courseId)).findFirst().get();

    }

   public List<Student> findAllStudentsByCourse(Long courseId){
        return findById(courseId).getStudents();
   }

   public Course addStudentToCourse(Student student, Course course){
        if(student.getUsername()==null || student.getPassword()==null ||
           student.getName()==null || student.getSurname()==null){
                throw new IllegalArgumentException();
        }
        if(course.getCourseId()==null || course.getName()==null
                || course.getDescription()==null)  {
                throw new IllegalArgumentException();
        }
        course.getStudents().removeIf(s-> s.getUsername().equals(student.getUsername()));
        course.getStudents().add(student);

        return course;

   }



}
