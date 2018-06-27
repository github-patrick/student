package com.example.divine.contoller;

import com.example.divine.model.Student;
import com.example.divine.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    private MockMvc mockMvc;
    private StudentController studentController;

    @Mock
    private StudentService studentService;


    @Before
    public void setUp() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");

        studentController = new StudentController(studentService);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).
                setViewResolvers(viewResolver).build();
    }

    @Test
    public void getAllStudents() throws Exception {
        mockMvc.perform(get(StudentController.RESOURCE_PATH)).
                andExpect(view().name("list"));
    }

    @Test
    public void newStudentForm() throws Exception {
        mockMvc.perform(get(StudentController.RESOURCE_PATH + "/new"))
        .andExpect(view().name("new"));
    }

    @Test
    public void createStudent()  throws Exception{
        mockMvc.perform(post(StudentController.RESOURCE_PATH))
                .andExpect(view().name("redirect:/students/"));
    }

    @Test
    public void showStudent() throws Exception{
        mockMvc.perform(get(StudentController.RESOURCE_PATH + "/1/show"))
                .andExpect(view().name("show"));
    }

    @Test
    public void deleteStudent() throws Exception {
        when(studentService.getStudent(new Long(1))).thenReturn(new Student());
        mockMvc.perform(get(StudentController.RESOURCE_PATH + "/1/delete"))
                .andExpect(view().name("redirect:/students/"));
    }
}