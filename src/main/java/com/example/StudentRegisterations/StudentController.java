package com.example.StudentRegisterations;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Student")
public class StudentController {
    private final  StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }
    @GetMapping("/Register")
    public  String ShowRegisterationForm(Model model){

        model.addAttribute("Student", new Student());
        return "Register";


    }
    //
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") Student student, Model model) {
        try {
            studentServices.RegisterStudent(student);
            return "redirect:/students/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentServices.getAllStudent());
        return "student-list";
    }

}
