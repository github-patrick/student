package com.example.divine.bdd.api.config;

import com.example.divine.bdd.api.utils.StudentRepo;
import com.example.divine.dao.StudentDao;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private StudentDao studentDao;

    @Bean
    public StudentRepo studentRepo() {
        return new StudentRepo(studentDao);
    }

    @Bean
    @Scope("prototype")
    public RequestSpecBuilder requestSpecBuilder() {
        return new RequestSpecBuilder();
    }

}
