package com.example.divine.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentNum;

    @NotNull
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "Surname should have at least 1 character")
    private String surname;

    @NotNull(message = "A student must study a course")
    @Size(min = 2)
    private String course;

    @Min(value = 16, message = "student must be 16 and above to enrol")
    @Max(value = 90, message = "student must be 90 or below")
    private int age;


}