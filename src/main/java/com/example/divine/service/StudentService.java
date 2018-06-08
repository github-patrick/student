package com.example.divine.service;

import com.example.divine.dao.StudentDao;
import com.example.divine.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void addStudent(Student student) {
       studentDao.save(student);
    }

    public Student getStudent(Long id) {
        Optional<Student> student = studentDao.findById(id);

        if (!student.isPresent()) {
            return null;
        }
        return student.get();
    }

    public void deleteStudent(Student student) {
        studentDao.delete(student);
    }

    public Iterable<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public void updateStudent(Student student) {
        studentDao.save(student);
    }

    public void patchStudent(Student student, Student studentToBePatched) {
        Student studentPatched = new Student();

        studentPatched.setStudentNum(student.getStudentNum());

        if (studentToBePatched.getAge() != 0) {
            student.setAge(studentToBePatched.getAge());
        }
        if (studentToBePatched.getCourse() != null) {
            student.setCourse(studentToBePatched.getCourse());
        }
        if (studentToBePatched.getSurname() != null) {
            student.setSurname(studentToBePatched.getSurname());
        }
        if (studentToBePatched.getFirstName() != null) {
            student.setFirstName(studentToBePatched.getFirstName());
        }

        studentDao.save(student);
    }
}
