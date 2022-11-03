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
        students.add(new Student("000001","xxxxx1",
                "Petre","Petrovski"));
        students.add(new Student("000002","xxxxx2",
                "Marija","Nikolovska"));
        students.add(new Student("000003","xxxxx3",
                "Stojan","Stojanovski"));
        students.add(new Student("000004","xxxxx4",
                "Riste","Ristevski"));
        students.add(new Student("000005","xxxxx5",
                "Teodora","Temelkovska"));


    }

    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream().filter(s-> s.getName().contains(text)
                || s.getSurname().contains(text)
                || s.getUsername().contains(text)
        ).collect(Collectors.toList());
    }
}
