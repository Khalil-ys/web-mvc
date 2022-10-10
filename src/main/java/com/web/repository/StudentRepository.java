package com.web.repository;

import com.web.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private DataSource dataSource;

    public void addStudentDb(Student student){
        try {
            Connection conn=dataSource.getConnection();
            PreparedStatement statement= conn
                    .prepareStatement
                            ("insert into students (name,surname,student_class,birthday," +
                                    "email,course_code,phone) values (?,?,?,?,?,?,?);");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3,student.getStudentClass());;
            statement.setDate(4,student.getBirthday());
            statement.setString(5,student.getEmail());
            statement.setString(6,student.getCourseCode());
            statement.setString(7,student.getPhone());
            statement.executeUpdate();
            statement.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Student> findAll(){
        List<Student>students=new ArrayList<>();
        try {
            Connection con= dataSource.getConnection();
            PreparedStatement statement=con.prepareStatement("select * from students");
            ResultSet result= statement.executeQuery();
            while (result.next()){
                Student s=new Student(result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getInt("student_class"));

                s.setBirthday(result.getDate("birthday"));
                s.setEmail(result.getString("email"));
                s.setCourseCode(result.getString("course_code"));
                s.setPhone(result.getString("phone"));
                students.add(s);
            }
            result.close();
            statement.close();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
    public void deleteById(Integer id){
        try {
            Connection con= dataSource.getConnection();
            PreparedStatement statement= con.prepareStatement("delete from students where id=?;");
            statement.setInt(1,id);
            statement.executeUpdate();
            statement.close();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Student findById(Integer id) {
        Student s=null;
        try {
            Connection c= dataSource.getConnection();
            PreparedStatement statement=c.prepareStatement("select * from students where id=?;");
            statement.setInt(1,id);
            ResultSet result= statement.executeQuery();
            if (result.next()){
                s=new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getInt("student_class"));
                s.setBirthday(result.getDate("birthday"));
                s.setEmail(result.getString("email"));
                s.setCourseCode(result.getString("course_code"));
                s.setPhone(result.getString("phone"));
            }
            result.close();
            statement.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public void editStudent(Student student) {
        try {
            Connection c= dataSource.getConnection();
            PreparedStatement statement= c.prepareStatement
                    ("update students set name=?,surname=?,student_class=?,birthday=?,email=?,course_code=?,phone=? where  id=?;");
            statement.setString(1, student.getName());
            statement.setString(2,student.getSurname());
            statement.setInt(3,student.getStudentClass());
            statement.setDate(4,student.getBirthday());
            statement.setString(5,student.getEmail());
            statement.setString(6,student.getCourseCode());
            statement.setString(7,student.getPhone());
            statement.setInt(8,student.getId());
            statement.executeUpdate();
            statement.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Student>findAllSearch(String name){
        List<Student> students=new ArrayList<>();
        try{
            Connection con= dataSource.getConnection();
            PreparedStatement statement= con.prepareStatement(
                    "select * from students where name=?;");
            statement.setString(1,name);
            ResultSet result= statement.executeQuery();
            while (result.next()){
                Student s=new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getInt("student_class"));

                s.setBirthday(result.getDate("birthday"));
                s.setEmail(result.getString("email"));
                s.setCourseCode(result.getString("course_code"));
                s.setPhone(result.getString("phone"));

                students.add(s);
            }
            result.close();
            statement.close();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
}
