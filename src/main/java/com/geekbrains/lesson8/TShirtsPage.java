package com.geekbrains.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TShirtsPage {
    private ElementsCollection sizes = $$(By.xpath("//ul[@id='ul_layered_id_attribute_group_1']//a"));

    @Step("Выбрать размер")
    public TShirtsPage selectSize(String size) {
        sizes.findBy(Condition.text(size)).click();
        return this;
    }

    private SelenideElement priceLeftSlider = $(By.xpath("//div[@class='layered_slider_container']//a[1]"));

    @Step("Выбрать цену")
    public TShirtsPage selectPrice(int amount) {
        actions().dragAndDropBy(priceLeftSlider, amount, 0).perform();
        return this;
    }

    private ElementsCollection tShirtsList = $$(By.xpath("//ul[@class='product_list grid row']/li"));

    private SelenideElement addToCartButton = $(By.xpath("//a[@title='Add to cart']"));

    @Step("Добавить рубашку в корзину по имени")
    public SuccessAddToCartPopup addTShirtToCartByName(String name) {
        tShirtsList.findBy(Condition.text(name)).hover();
        addToCartButton.click();
        return page(SuccessAddToCartPopup.class);
    }
}
