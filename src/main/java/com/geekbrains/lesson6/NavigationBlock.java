package com.geekbrains.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationBlock extends BaseView {
    public NavigationBlock(WebDriver driver) {
        super(driver);
    }

    private final static String SIGN_IN_XPATH_LOCATOR = "//a[contains(@title,'Log in')]";

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement womenButton;

    @FindBy(xpath = "//ul[contains(@class,'submenu')]//a[.='T-shirts']")
    private WebElement tShirtsSubmenuTShirtsButton;

    @FindBy(xpath = "//a[@title='Contact Us']")
    private WebElement contactsUsLink;

    @FindBy(xpath = "//a[contains(@title,'Log in')]")
    private WebElement signInLink;


    @Step("Клик на кнопку 'Рубашки' в подменю 'Женщины'")
    public TShirtsPage clickTShirtsButtonInWomenSubmenu() {
        actions.moveToElement(womenButton)
                .build()
                .perform();
        webDriverWait.until(ExpectedConditions.visibilityOf(tShirtsSubmenuTShirtsButton));
        tShirtsSubmenuTShirtsButton.click();
        return new TShirtsPage(driver);
    }

    @Step("Клик на кнопку 'Contact us'")
    public ContactUsPage clickContactUs() {
        webDriverWait.until(ExpectedConditions.visibilityOf(contactsUsLink));
        contactsUsLink.click();
        return new ContactUsPage(driver);
    }

    @Step("Клик на кнопку 'Sign in'")
    public LoginPage clickSignIn() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGN_IN_XPATH_LOCATOR)));
        webDriverWait.until(ExpectedConditions.visibilityOf(signInLink));
        signInLink.click();
        return new LoginPage(driver);
    }

}
