package com.example.divine.bdd.api.utils;

import com.example.divine.dao.StudentDao;
import com.example.divine.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo {

    private StudentDao studentDao;

    @Autowired
    public StudentRepo(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentDao.findAll().iterator().forEachRemaining(students::add);

        return students;
    }

    public void addStudent(Student student) {
        studentDao.save(student);
    }
}
