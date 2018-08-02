package com.example.divine.contoller;

import com.example.divine.model.Student;
import com.example.divine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(StudentController.RESOURCE_PATH)
public class StudentController {

    public static final String RESOURCE_PATH = "/students";
    private List<String> coursesList =Arrays.asList("Computer Science", "Maths", "Art", "Physics", "Chemistry", "Biology",
            "Anthropology", "Archaeology", "Economics", "French", "Italian", "Medicine", "Sports Science", "Sociology");

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllStudents(Model model) {

        List<Student> students = new ArrayList<>();
        studentService.getAllStudents().forEach(student -> students.add(student));
        model.addAttribute("students",students);
        return "list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newStudentForm(Model model) {
        model.addAttribute("courseList", coursesList );
        model.addAttribute("student", new Student());
        return "new";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createStudent(@ModelAttribute Student student) {

        studentService.addStudent(student);
        return "redirect:/students/";
    }

    @RequestMapping(value="{id}/show", method = RequestMethod.GET)
    public String showStudent(@PathVariable Long id, Model model) {
        model.addAttribute("courseList", coursesList );
        model.addAttribute("student", studentService.getStudent(id));
        return "show";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(studentService.getStudent(id));
        return "redirect:/students/";
    }


}
