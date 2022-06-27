package com.geekbrains.hw4;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutomationPractiseHW4 {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    @Story("Поиск платья")
    @Description("Проверка поисковой строки")
    public void searchDressTest()
    {
        driver.get("http://automationpractice.com/index.php?id_category=5&controller=category#/");

        String searchWord = "Dress";
        driver.findElement(By.xpath("//input[@name='search_query']")).click();
        driver.findElement(By.name("search_query")).sendKeys(searchWord);
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();;
        WebElement searchItem = driver.findElement(By.xpath("(//div[@class='right-block']//a[@class='product-name'])[1]"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='heading-counter']")));
        Assert.assertEquals(true, searchItem.getText().contains(searchWord));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
