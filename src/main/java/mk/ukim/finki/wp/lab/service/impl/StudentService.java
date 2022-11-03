package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements mk.ukim.finki.wp.lab.service.StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if(text==null || text.isEmpty()){
            return null;
        }
        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(username==null || password==null || name==null || surname == null){
            throw new IllegalArgumentException();
        }

        Student s = new Student(username,password,name,surname);
        studentRepository.findAllStudents().add(s);
        return s;

    }
}
