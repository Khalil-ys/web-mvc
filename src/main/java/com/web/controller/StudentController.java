package com.web.controller;


import com.web.model.Student;
import com.web.service.LanguageService;
import com.web.service.SectorService;
import com.web.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private LanguageService languageService;

    @GetMapping("/list")
    @PreAuthorize(value="hasAuthority('read:all:students')")
    public String showStudents(Model model){

        model.addAttribute("students",studentService.findAll());
        return "students";
    }

    @GetMapping("/list/param")
    public String showStudentsParam(Model model,
                                    @RequestParam(name = "student-name") String studentName,
                                    @RequestParam(name = "student-surname",required = false,defaultValue ="Carlos") String studentSurname
                                    ){
        System.out.println(studentSurname);
        List<Student> studentList=studentService.findAll();

        List<Student> studentListFiltred=new ArrayList<>();
        for (Student s:studentList){
            if (s.getName().equals(studentName)){
                studentListFiltred.add(s);
            }
        }
        model.addAttribute("students",studentListFiltred);
        return "students";
    }
    @GetMapping("/new")
    @PreAuthorize(value = "hasAuthority('open:new:student:page')")
    public String showAddStudentPage(Model model){
        Student s=new Student();
        s.setId(0);
        //s.setName("Janna");
        model.addAttribute("student",s);
        model.addAttribute("header","Tələbə qeydiyyatı");
        model.addAttribute("sectors",sectorService.findAll());
        model.addAttribute("languages",languageService.findAll());
        return "save-student";
    }

    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('save:student')")
    public String saveStudent(@Valid @ModelAttribute(name = "student") Student student, BindingResult result,Model model){
        //studentService.addStudent(student);
        if (result.hasErrors()){
            if (student.getId()==0){
                model.addAttribute("header","Telebe qeydiyyati");
            }else {
                model.addAttribute("header","Telebe redaktesi");
            }
            return "save-student";
        }
        if (student.getId()==0){
            studentService.addStudent(student);
        }
        else if (student.getId()>0){
            studentService.editStudent(student);
        }
        return "redirect:/students/list";
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize(value = "hasAuthority('delete:student')")
    public String deleteById(@PathVariable(name = "id") Integer id,Model model){
        boolean result=studentService.deleteById(id);
        if (!result){
            model.addAttribute("errorMessage","Telebe tapilmadi!!! ID= "+id);
            return "error";
        }
        return "redirect:/students/list";
    }
    @GetMapping("/edit/{id}")
    @PreAuthorize(value = "hasAuthority('open:edit:student:page')")
    public String showEditStudentPage(@PathVariable(name = "id")Integer id,Model model){
        Student s=studentService.findById(id);
        model.addAttribute("student",s);
        model.addAttribute("header","Tələbə Redaktəsi");
        model.addAttribute("sectors",sectorService.findAll());
        model.addAttribute("languages",languageService.findAll());
        return "save-student";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        StringTrimmerEditor trmmer=new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class,trmmer);
    }
    @GetMapping("list/search")
    @PreAuthorize(value = "hasAuthority('search:student')")
    public String searchStudents(@RequestParam(name = "studentName")String studentName, Model model){
        model.addAttribute("students",studentService.findAllSearch(studentName));
        return "students";
    }

}
