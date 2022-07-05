package com.geekbrains.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseView {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement singInButton;

    @Step("Логин")
    public MyAccountPage login(String email, String password) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        singInButton.click();
        return new MyAccountPage(driver);
    }
}
