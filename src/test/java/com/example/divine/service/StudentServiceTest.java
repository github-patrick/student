package com.example.divine.service;

import com.example.divine.dao.StudentDao;
import com.example.divine.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentServiceTest {

    private StudentService studentService;
    private List<Student> students;


    @Mock
    private StudentDao studentDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentService(studentDao);
        students = new ArrayList();

        Student student = Student.builder().firstName("Patrick").surname("Ugwu").course("Mathematics").age(31)
                .createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).build();
        students.add(student);
        students.add(new Student());
        students.add(new Student());
    }

    @Test
    public void addStudent() {
        Student student = new Student();
        studentService.addStudent(student);
        verify(studentDao, times(1)).save(student);
    }

    @Test
    public void getStudent() {
        Long id = new Long(1);
        when(studentDao.findById(id)).thenReturn(Optional.of(new Student()));
        studentService.getStudent(id);
        verify(studentDao, times(1)).findById(id);
    }


    @Test
    public void shouldGetAllStudents() {
        when(studentDao.findAll()).thenReturn(null);
        studentService.getAllStudents();
        verify(studentDao, times(1)).findAll();
    }

    @Test
    public void shouldDeleteStudent() {
        Long id = new Long(1);
        when(studentDao.findById(id)).thenReturn(Optional.of(new Student()));

        studentService.deleteStudent(studentService.getStudent(id));
        verify(studentDao, times(1)).delete(new Student());
    }

    @Test
    public void shouldPatchStudent() {
        Long id = new Long(1);
        when(studentDao.findById(id)).thenReturn(Optional.of(new Student()));

        studentService.patchStudent(studentService.getStudent(id), new Student());
        verify(studentDao, times(1)).save(new Student());

    }
}