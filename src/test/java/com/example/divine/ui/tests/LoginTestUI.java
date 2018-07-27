package com.example.divine.ui.tests;


import com.example.divine.ui.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTestUI {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    @Ignore
    public void shouldLoginValidUser() throws Exception {
        driver.get("http://localhost:8080/login");

        loginPage.enterTextForUsername("user");
        loginPage.enterTextForPassword("password");
        loginPage.clickOnSubmitButton();
    }

    @After
    public void tearDown() {
        driver.close();
    }


}
