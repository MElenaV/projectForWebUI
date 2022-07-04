package com.geekbrains.lesson6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuccessAddToCartPopup extends BaseView {
    public SuccessAddToCartPopup(WebDriver driver) {
        super(driver);
    }

    private final static String SUCCESS_MESSAGE_XPATH_LOCATOR = "//div[contains(@class,'layer_cart_product')]/h2";

    @FindBy(xpath = SUCCESS_MESSAGE_XPATH_LOCATOR)
    private WebElement successMessage;

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    private WebElement totalSumma;

    @Step("Проверить корректность итоговой суммы")
    public void checkTotalSumm(String summa) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUCCESS_MESSAGE_XPATH_LOCATOR)));
        Assertions.assertAll(
                () -> Assertions.assertTrue(successMessage.isDisplayed()),
                () -> Assertions.assertEquals(summa, totalSumma.getText())
        );
    }
}
