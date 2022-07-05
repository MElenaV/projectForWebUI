package hw6;

import com.geekbrains.lesson6.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AutomationPractiseHW6 {
  WebDriver driver;

  @BeforeAll
  static void registerDriver() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void initDriver() {
    driver = new ChromeDriver();
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
            .checkSuccessSendMessage("Your message has been successfully sent to our team.")
            .returnToHome();
  }

  @AfterEach
  void tearDown() {
    driver.quit();
  }

}
