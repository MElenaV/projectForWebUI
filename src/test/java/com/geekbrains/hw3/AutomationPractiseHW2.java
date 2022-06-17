package com.geekbrains.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AutomationPractiseHW2 {

    @Test
    @Story("Покупка рубашки")
    @Description("Проверка добавления рубашки в корзину")
    public void searchDressTest()
    {
        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://automationpractice.com/index.php?id_category=5&controller=category#/");
        String searchWord = "Dress";
        WebElement searchInput =  webDriver.findElement(By.xpath("//input[@name='search_query']"));
        searchInput.click();
        webDriver.findElement(By.name("search_query")).sendKeys(searchWord);
        WebElement searchButton = webDriver.findElement(By.xpath("//button[@name='submit_search']"));
        searchButton.click();
        WebElement searchItem = webDriver.findElement(By.xpath("(//div[@class='right-block']//a[@class='product-name'])[1]"));
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='heading-counter']")));
        Assert.assertEquals(true, searchItem.getText().contains(searchWord));

        webDriver.quit();
    }
}
