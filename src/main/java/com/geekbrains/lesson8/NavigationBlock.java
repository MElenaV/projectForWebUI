package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NavigationBlock {
    private SelenideElement womenButton = $(By.xpath("//a[@title='Women']"));

    private SelenideElement tShirtsSubmenuTShirtsButton = $(By.xpath("//ul[contains(@class,'submenu')]//a[.='T-shirts']"));

    private SelenideElement contactsUsLink = $(By.xpath("//a[@title='Contact Us']"));

    private SelenideElement signInLink = $(By.xpath("//a[contains(@title,'Log in')]"));

    @Step("Клик на кнопку 'Рубашки' в подменю 'Женщины'")
    public TShirtsPage clickTShirtsButtonInWomenSubmenu() {
        womenButton.hover();
        tShirtsSubmenuTShirtsButton.click();
        return page(TShirtsPage.class);
    }

    @Step("Клик на кнопку 'Contact us'")
    public ContactUsPage clickContactUs() {
        contactsUsLink.click();
        return page(ContactUsPage.class);
    }

    @Step("Клик на кнопку 'Sign in'")
    public LoginPage clickSignIn() {
        signInLink.click();
        return page(LoginPage.class);
    }
}

