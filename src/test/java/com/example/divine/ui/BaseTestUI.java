package com.example.divine.ui;

import com.example.divine.DemoApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;

@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class BaseTestUI {

    protected WebDriver driver;

    @Value("${com.example.divine.ui.selenium.base.url}")
    private String domain;

    @LocalServerPort
    private int port;

    protected String baseUrl;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = domain + ":" + port;
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
