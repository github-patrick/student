package com.example.divine.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id= "password")
    private WebElement password;

    @FindBy(id= "submitBtn")
    private WebElement submitBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void enterTextForUsername(String username) {
       this.username.sendKeys(username);
    }

    public void enterTextForPassword(String password) {
        this.password.sendKeys(password);
    }

    public void clickOnSubmitButton() {
        submitBtn.click();
    }


}
