package com.example.divine.contoller;

import com.example.divine.exception.CustomizedResponseEntityExceptionHandler;
import com.example.divine.model.Student;
import com.example.divine.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    private MockMvc mockMvc;

    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private Student student;

    @Before
    public void setUp() {
        studentController = new StudentController(studentService);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new CustomizedResponseEntityExceptionHandler())
                .build();

        student = Student.builder().firstName("Mark")
                .surname("Anthony")
                .age(21)
                .course("Computer Science")
                .studentNum(new Long(1)).build();
    }

    @Test
    public void addStudent() throws Exception {
        mockMvc.perform(post(StudentController.STUDENT_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.studentNum", is(1)))
                .andExpect(jsonPath("$.firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$.surname", is(student.getSurname())))
                .andExpect(jsonPath("$.course", is(student.getCourse())))
                .andExpect(jsonPath("$.age", is(student.getAge())));
    }

    @Test
    public void getAllStudents() throws Exception {
        mockMvc.perform(get(StudentController.STUDENT_PATH)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudent() throws Exception {
        when(studentService.getStudent(new Long(1))).thenReturn(new Student());

        mockMvc.perform(get(StudentController.STUDENT_PATH + "/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void getStudentDoesNotExist() throws Exception {
        mockMvc.perform(get(StudentController.STUDENT_PATH + "/2")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("student with id 2 does not exist"));
    }

    @Test
    public void deleteStudent() throws Exception {
        when(studentService.getStudent(new Long(1))).thenReturn(new Student());
        mockMvc.perform(delete(StudentController.STUDENT_PATH + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentDoesNotExist() throws Exception {
        when(studentService.getStudent(new Long(132))).thenReturn(null);

        mockMvc.perform(delete(StudentController.STUDENT_PATH + "/132" ))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateStudent() throws Exception {
        when(studentService.getStudent(new Long(1))).thenReturn(Student.builder().studentNum(new Long(1)).build());

        mockMvc.perform(put(StudentController.STUDENT_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudentThatDoesNotExist() throws Exception {
        when(studentService.getStudent(new Long(1))).thenReturn(null);

        mockMvc.perform(put(StudentController.STUDENT_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateStudentThatWithAInvalidValue() throws Exception {
        Student student = new Student();
        student.setStudentNum(new Long(2));
        when(studentService.getStudent(new Long(1))).thenReturn(student);

        this.student.setStudentNum((long) 1);
        mockMvc.perform(put(StudentController.STUDENT_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(this.student)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void patchStudent() throws Exception {
        when(studentService.getStudent(new Long(1))).thenReturn(Student.builder().studentNum(new Long(1)).build());

        mockMvc.perform(patch(StudentController.STUDENT_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isNoContent());
    }
}