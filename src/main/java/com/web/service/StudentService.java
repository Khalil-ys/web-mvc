package com.web.service;

import com.web.model.Sector;
import com.web.model.Student;
import com.web.repository.StudentRepository;
import com.web.repository.StudentRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentRepositoryJpa repositoryJpa;

    private List<Student> studentList=new ArrayList<>();

    {
        studentList.add(new Student(1,"Messi","Lionel",5));
        studentList.add(new Student(2,"Isak","Nyuton",6));
        studentList.add(new Student(3,"Messi","Lionel",8));
        studentList.add(new Student(4,"Charlie","Chaplin",8));
        studentList.add(new Student(5,"Pifagor","Pifagor",9));
        studentList.add(new Student(6,"Roberto","Carlos",7));
        studentList.add(new Student(7,"Pifagor","Hendese",9));
    }
    public void addStudent(Student student){
        //studentList.add(student); add student to list
        //repository.addStudentDb(student);  // ad student to db.
        repositoryJpa.save(student);
    }

    public List<Student>findAll(){
        //return repository.findAll();
        List<Student>students=repositoryJpa.findAll();
        for (Student student:students){
            if (student.getSector()==null){
                student.setSector(new Sector());
            }
        }
        return students;
    }

    public boolean deleteById(Integer id) {
        //repository.deleteById(id);
        boolean studentExists=repositoryJpa.findById(id).isPresent();
        if (studentExists){
            repositoryJpa.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
    public Student findById(Integer id){
        //return repository.findById(id);
        return repositoryJpa.findById(id).get();
    }
    public void editStudent(Student student){
        //repository.editStudent(student);
        repositoryJpa.save(student);
    }
    public List<Student> findAllSearch(String surname){
        //return repository.findAllSearch(name);
        //return repositoryJpa.findAllByName(name);
        return repositoryJpa.searchStudentBySurname(surname);
    }
}
