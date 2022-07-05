package com.geekbrains.hw6;

import com.geekbrains.lesson6.LoginPage;
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


public class AutomationPractiseHW6 {
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
  @Story("Отправка обратной связи")
  @Description("Проверка отправки обратной связи")
  @Issue("SPE-5164")
  void sendMessage() throws InterruptedException {
    new LoginPage(driver)
            .login("spartalex93@test.test", "123456")
            .navigationBlock.clickContactUs()
            .selectSelectSubjectHeading("Customer service")
            .fillInputEmail("123@mail.ru")
            .fillMessage("Добрый день!")
            .loadFile()
            .sendMessage()
            .checkSuccessSendMessage("Your message has been successfully sent to our team.");
           // .returnToHome();
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
