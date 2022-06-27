package com.geekbrains.lesson5;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionsExamples {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    ////div[@data-test='PAGE-SECTION-HEADER' and contains(.,'Кино')]/following-sibling::div//a[@data-test='LINK ITEM-URL']
    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    void dragAndDrop() {
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable")))
                .build()
                .perform();
        assertEquals("Dropped!", driver.findElement(By.id("droppable")).getText());
    }

    @Test
    void selectExample() {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("dateOfBirthInput")).click();
        Select yearSelect = new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']")));
        yearSelect.selectByVisibleText("2000");
        assertEquals("2000", yearSelect.getFirstSelectedOption().getText());
    }

    @Test
    void loafFile() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(275, 667));
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("uploadPicture")).sendKeys("/Users/spartalex/Downloads/Задание № 6.rar");
        Thread.sleep(5000);
    }

    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        //window.localStorage.clear() - удаление записей из localstorage
        driver.quit();
    }
}
