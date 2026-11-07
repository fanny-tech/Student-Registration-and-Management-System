package com.example.StudentRegisterations;

import com.example.StudentRegisterations.Student;
import com.example.StudentRegisterations.model.Student;
import com.example.StudentRegisterations.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {
    private final StudentServices studentRepository;

    public StudentServices(StudentServices studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void registerStudent( Student student) {
        if (  student.getAge() <= 18) {
            throw new IllegalArgumentException("Student age must be greater than 18");
        }
       studentRepository.registerStudent(student);
    }

    public List<String> getAllStudents() {

        return studentRepository.getAllStudents();
    }


}