package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {
    private static List<Teacher> teacherList = new ArrayList<>();


    @PostConstruct
    public void init(){
        teacherList.add(new Teacher(11L,"Marija","Kostovska"));
        teacherList.add(new Teacher(12L,"Petre","Kostovski"));
        teacherList.add(new Teacher(13L,"Mitre","Mitreski"));
        teacherList.add(new Teacher(14L,"Petar","Petreski"));
        teacherList.add(new Teacher(15L,"Sonja","Petreska"));
    }

    public List<Teacher> findAll(){
        return teacherList;
    }

}
