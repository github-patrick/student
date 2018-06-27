package com.example.divine.contoller;


import com.example.divine.exception.StudentNotFoundException;
import com.example.divine.model.Student;
import com.example.divine.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(StudentApiController.STUDENT_PATH)
public class StudentApiController {

    public static final String STUDENT_PATH = "/students";

    private final StudentService studentService;

    @Autowired
    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity(student, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        if (studentService.getStudent(id) == null) {
            throw new StudentNotFoundException("student with id " + id + " does not exist");
        }
        return new ResponseEntity(studentService.getStudent(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@Valid @RequestBody Student studentInfo, @PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (studentInfo.getStudentNum().intValue() != student.getStudentNum().intValue()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BeanUtils.copyProperties(studentInfo, student, "createdDate");
        studentService.updateStudent(student);
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @PatchMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity patchStudent(@RequestBody Student studentInfo, @PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            studentService.patchStudent(student, studentInfo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
