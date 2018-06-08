package com.example.divine.bdd.api.utils;

import com.example.divine.model.Student;
import com.github.javafaker.Faker;


public class TestStudentCommonUtils {

    private static Faker faker = new Faker();


    public static Student createDefaultStudent() {
        Student student = new Student();
        student.setFirstName(faker.firstName());
        student.setSurname(faker.lastName());
        student.setCourse(faker.country());
        student.setAge(20);

        return student;
    }

    public static Student createStudent(String firstName, String surname, int age, String course) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setSurname(surname);
        student.setCourse(course);
        student.setAge(age);

        return student;
    }

    public static Student createInvalidStudent() {
        Student student = new Student();
        student.setFirstName(faker.firstName());
        student.setSurname(faker.lastName());
        student.setCourse(faker.country());
        student.setAge(1);

        return student;
    }



}
