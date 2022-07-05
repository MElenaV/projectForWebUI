package com.geekbrains.lesson6;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

public class ContactUsPage extends BaseView {
  public ContactUsPage(WebDriver driver) {
    super(driver);
  }

  private final static String SUCCESS_MESSAGE_XPATH_LOCATOR = "//p[contains(@class,'alert-success')]";

  @FindBy(xpath = "//h1[contains(text(),'Contact us')]")
  private WebElement contactsUsHeader;

  @FindBy(xpath = "//select[@id='id_contact']")
  private WebElement selectSubjectHeading;

  @FindBy(xpath = "//select[@id='id_contact']/option")
  private List<WebElement> optionsSelectSubjectHeading;

  @FindBy(xpath = "//input[@id='email']")
  private WebElement inputEmail;

  @FindBy(xpath = "//button[@id='submitMessage']")
  private WebElement buttonSendMessage;

  @FindBy(xpath = "//textarea[@id='message']")
  private WebElement textareaMessage;

  @FindBy(xpath = "//p[contains(@class,'alert-success')]")
  private WebElement successfulMessage;

  @FindBy(xpath = "//a[@title='Return to Home']")
  private WebElement iconReturnToHome;

  @Step("Выбрать заголовок письма")
  public ContactUsPage selectSelectSubjectHeading(String option) {
    webDriverWait.until(ExpectedConditions.visibilityOf(contactsUsHeader));
    optionsSelectSubjectHeading.stream().filter(s -> s.getText().contains(option)).findFirst().get().click();
    return this;
  }

  @Step("Заполнить email адрес для отправки письма")
  public ContactUsPage fillInputEmail(String email) {
    inputEmail.clear();
    inputEmail.sendKeys(email);
    return this;
  }

  @Step("Загрузить файл")
  public ContactUsPage loadFile() throws InterruptedException {
    driver.findElement(By.id("fileUpload")).sendKeys(new File("./src/test/resources/kvokka.jpg").getAbsolutePath());
    // Thread.sleep(5000);
    return this;
  }


  @Step("Заполнить текст сообщения")
  public ContactUsPage fillMessage(String message) {
    textareaMessage.sendKeys(message);
    return this;
  }

  @Step("Отправить сообщение")
  public ContactUsPage sendMessage() {
    buttonSendMessage.click();
    return this;
  }

  @Step("Проверить успешную отправку сообщения")
  public ContactUsPage checkSuccessSendMessage(String successMessage) {
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUCCESS_MESSAGE_XPATH_LOCATOR)));
    Assertions.assertAll(
            () -> Assertions.assertTrue(successfulMessage.isDisplayed()),
            () -> Assertions.assertEquals(successMessage, successfulMessage.getText())
    );
    return this;
  }

  @Step("Вернуться на главную страницу сайта")
  public MainPage returnToHome() {
    iconReturnToHome.click();
    return new MainPage(driver);
  }


}
