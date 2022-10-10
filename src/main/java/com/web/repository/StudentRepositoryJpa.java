package com.web.repository;

import com.web.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepositoryJpa extends JpaRepository<Student,Integer> {

    List<Student>findAllByName(String name);

    @Query(value = "select * from students where surname like %?1%",nativeQuery = true)
    List<Student> searchStudentBySurname(String surname);
}
