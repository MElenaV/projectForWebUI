package com.geekbrains.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TShirtsPage extends BaseView {
    public TShirtsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_1']//a")
    private List<WebElement> sizes;

    @Step("Выбрать размер")
    public TShirtsPage selectSize(String size) {
        sizes.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//div[@class='layered_slider_container']//a[1]")
    private WebElement priceLeftSlider;

    @Step("Выбрать цену")
    public TShirtsPage selectPrice(int amount) {
        actions.dragAndDropBy(priceLeftSlider, amount, 0);
        return this;
    }

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    private List<WebElement> tShirtsList;

    @FindBy(xpath = "//a[@title='Add to cart']")
    private WebElement addToCartButton;

    @Step("Добавить рубашку в корзину по имени")
    public SuccessAddToCartPopup addTShirtToCartByName(String name) {
        actions.moveToElement(tShirtsList.stream().filter(t -> t.getText().contains(name)).findFirst().get())
                .build()
                .perform();

        addToCartButton.click();
        return new SuccessAddToCartPopup(driver);
    }
}
