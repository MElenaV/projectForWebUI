package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    private SelenideElement emailField = $(By.id("email"));

    private SelenideElement passwordField = $(By.id("passwd"));

    private SelenideElement singInButton = $(By.id("SubmitLogin"));

    @Step("Логин")
    public MyAccountPage login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        singInButton.click();
        return page(MyAccountPage.class);
    }
}
