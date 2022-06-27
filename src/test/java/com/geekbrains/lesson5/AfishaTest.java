package com.geekbrains.lesson5;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AfishaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;

    ////div[@data-test='PAGE-SECTION-HEADER' and contains(.,'Кино')]/following-sibling::div//a[@data-test='LINK ITEM-URL']
    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://afisha.ru");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    void likeRandomMovie() throws InterruptedException {
        webDriverWait.until(driver1 -> driver.findElements(
                By.xpath("//div[@data-test='PAGE-SECTION-HEADER' and contains(.,'Кино')]" +
                        "/following-sibling::div//a[@data-test='LINK ITEM-URL']/ancestor::div[@data-test='ITEM']//h2")).size() > 0);
        List<WebElement> filmsList = driver.findElements(
                By.xpath("//div[@data-test='PAGE-SECTION-HEADER' and contains(.,'Кино')]" +
                        "/following-sibling::div//a[@data-test='LINK ITEM-URL']/ancestor::div[@data-test='ITEM']//h2"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filmsList.get(1));
        //filmsList.stream().filter(f -> f.getText().contains("Три кота")).findFirst().get().click();
        filmsList.get(1).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")));
        driver.findElement(By.xpath(
                "//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")).click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'login')]")));

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
        Assertions.assertTrue(driver.findElement(By.id("login")).isDisplayed());
    }

    @Test
    void clickToOkkoLink() {
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("//a[.='КИНО']")))
                .build()
                .perform();

        driver.findElement(By.xpath("//div[@data-test='SUGGEST']//a[.='Скоро онлайн в Okko']")).click();

        Assertions.assertTrue(driver.getCurrentUrl().contains("okko-soon"));
    }

    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        //window.localStorage.clear() - удаление записей из localstorage
        driver.quit();
    }
}
