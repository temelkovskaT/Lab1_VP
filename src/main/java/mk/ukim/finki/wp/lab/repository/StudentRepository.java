package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    private static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("petre.petre","xxxxx1",
                "Petre","Petrovski"));
        students.add(new Student("marija.m","xxxxx2",
                "Marija","Nikolovska"));
        students.add(new Student("stojan.s","xxxxx3",
                "Stojan","Stojanovski"));
        students.add(new Student("riste.r","xxxxx4",
                "Riste","Ristevski"));
        students.add(new Student("tea.t","xxxxx5",
                "Teodora","Temelkovska"));


    }

    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream().filter(s-> s.getUsername().contains(text)
                || s.getSurname().contains(text)
                || s.getName().contains(text)
        ).collect(Collectors.toList());
    }
}
