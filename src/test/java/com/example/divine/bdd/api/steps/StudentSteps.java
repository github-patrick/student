package com.example.divine.bdd.api.steps;

import com.example.divine.bdd.api.utils.StudentRepo;
import com.example.divine.bdd.api.utils.TestStudentCommonUtils;
import com.example.divine.contoller.StudentApiController;
import com.example.divine.model.Student;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StudentSteps {

    private Student student;

    private Response response;

    @Autowired
    private RequestSpecBuilder request;

    @Autowired
    private StudentRepo studentRepo;

    @Given("I am on the students endpoint")
    public void i_am_on_the_students_endpoint() {
        request.setBasePath(StudentApiController.STUDENT_PATH);
    }

    @Given("I have a valid student")
    public void i_have_a_valid_student() {
        student = TestStudentCommonUtils.createDefaultStudent();
    }

    @Given("I have an invalid student")
    public void i_have_an_invalid_student() {
        student = TestStudentCommonUtils.createInvalidStudent();
    }

    @Given("I set the header {string} as {string}")
    public void i_set_the_header_as(String header, String value) {
        request.addHeader(header,value);
    }

    @Given("I have created {int} students")
    public void i_have_created_students(Integer size) {
        for (int i=0; i<size; i++) {
            studentRepo.addStudent(TestStudentCommonUtils.createDefaultStudent());
        }
    }

    @Given("I have a student to update")
    public void i_have_a_student_to_update() {
        student = given().log().all().spec(request.build()).get("1")
                .andReturn().as(Student.class);
    }

    @When("I send a request to create the student")
    public void i_send_a_request_to_create_the_student() {
        request.setBody(student);
        request.log(LogDetail.ALL);
        response = given().spec(request.build()).post();
    }

    @When("I send a request to update the student")
    public void i_send_a_request_to_update_the_student() {
        request.setBody(student);
        request.log(LogDetail.ALL);
        response = given().spec(request.build()).put(String.valueOf(student.getStudentNum()));
    }

    @When("I set the student first name to {string}")
    public void i_set_the_student_first_name_to(String firstName) {
        student.setFirstName(firstName);
    }

    @Then("I should see a {int} response")
    public void i_should_see_a_response(Integer statusCode) {
        response.then().statusCode(statusCode).log().all();
    }

    @Then("I should see the student exists")
    public void i_should_see_the_student_exists() {
        List<Student> students = new ArrayList<>();
        studentRepo.getAllStudents().iterator().forEachRemaining(students::add);

        assertEquals(students.size(), 1);

    }

    @Then("I should see that the student first name is {string}")
    public void i_should_see_that_the_student_first_name_is(String firstname) {
        student = given().log().all().spec(request.build()).get("1")
                .andReturn().as(Student.class);

        assertEquals(student.getFirstName(), firstname);
    }

}
