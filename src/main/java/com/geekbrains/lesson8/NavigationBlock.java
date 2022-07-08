package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NavigationBlock {
    private SelenideElement womenButton = $(By.xpath("//a[@title='Women']"));

    private SelenideElement tShirtsSubmenuTShirtsButton = $(By.xpath("//ul[contains(@class,'submenu')]//a[.='T-shirts']"));

    @Step("Клик на кнопку 'Рубашки' в подменю 'Женщины'")
    public TShirtsPage clickTShirtsButtonInWomenSubmenu() {
        womenButton.hover();
        tShirtsSubmenuTShirtsButton.click();
        return page(TShirtsPage.class);
    }
}

