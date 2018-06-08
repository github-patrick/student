package com.example.divine.bdd.api.hooks;

import com.example.divine.DemoApplication;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DemoApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class StudentHook {

    @Value("${com.example.divine.bdd.api.restassured.base.url}")
    protected String baseUrl;

    @LocalServerPort
    protected int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = baseUrl;
    }
}
