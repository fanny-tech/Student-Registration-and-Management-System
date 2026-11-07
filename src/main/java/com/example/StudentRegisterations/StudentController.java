package com.example.StudentRegisterations;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model) {
        try {
            Student student = studentServices.getStudentById(id);
            model.addAttribute("student", student);
            return "student-detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }
    }

}
