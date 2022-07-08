package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SuccessAddToCartPopup {
    private SelenideElement successMessage = $(By.xpath("//div[contains(@class,'layer_cart_product')]/h2"));

    private SelenideElement totalSumma = $(By.xpath("//span[@class='ajax_block_cart_total']"));

    @Step("Проверить корректность итоговой суммы")
    public void checkTotalSumm(String summa) {
        Assertions.assertAll(
                () -> successMessage.shouldBe(visible),
                () -> totalSumma.shouldHave(text(summa))
        );
    }
}
