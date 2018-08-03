package com.example.divine.ui.tests;


import com.example.divine.ui.BaseTestUI;
import com.example.divine.ui.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LoginTestUI extends BaseTestUI {

    private LoginPage loginPage;
    private static final String LOGIN_PATH = "/login";

    @Before
    public void setUpLoginTest() {
        loginPage = new LoginPage(driver);
        driver.get(baseUrl + LOGIN_PATH);
    }

    @Test
    public void shouldLoginValidUser() {

        loginPage.enterTextForUsername("user");
        loginPage.enterTextForPassword("password");
        loginPage.clickOnSubmitButton();

        //assertEquals("http://localhost:8081", driver.getCurrentUrl());
    }


}
