package com.geekbrains.lesson6;

import com.beust.ah.A;
import com.geekbrains.lesson7.AdditionalAllureSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Feature("Добавление в корзину")
public class AutomationPractiseTest {
    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new AdditionalAllureSteps()).decorate(new ChromeDriver());
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @Test
    @Story("Покупка рубашки")
    @Description("Проверка добавления рубашки в корзину")
    @Issue("SPE-1234")
    @TmsLink("123")
    void putTShirtToCart() {
        new LoginPage(driver)
                .login("spartalex93@test.test", "123456")
                .navigationBlock.clickTShirtsButtonInWomenSubmenu()
                .selectSize("S")
                .selectPrice(100)
                .addTShirtToCartByName("Faded")
                .checkTotalSumm("$18.51");
    }

    @AfterEach
    void killDriver() {
        driver.get("https://avito.ru");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry logEntry: logEntries) {
            Allure.addAttachment("Элемент лога браузера", logEntry.getMessage());
        }

        driver.quit();
    }
}
