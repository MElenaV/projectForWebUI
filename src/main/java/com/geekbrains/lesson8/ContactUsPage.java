package com.geekbrains.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.geekbrains.lesson6.BaseView;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ContactUsPage {

  private final static String SUCCESS_MESSAGE_XPATH_LOCATOR = "//p[contains(@class,'alert-success')]";

  private SelenideElement contactsUsHeader = $(By.xpath("//h1[contains(text(),'Contact us')]"));

  private SelenideElement selectSubjectHeading = $(By.xpath("//select[@id='id_contact']"));

  private ElementsCollection optionsSelectSubjectHeading = $$(By.xpath("//select[@id='id_contact']/option"));

  private SelenideElement inputEmail = $(By.xpath("//input[@id='email']"));

  private SelenideElement buttonSendMessage = $(By.xpath("//button[@id='submitMessage']"));

  private SelenideElement textareaMessage = $(By.xpath("//textarea[@id='message']"));

  private SelenideElement successfulMessage = $(By.xpath("//p[contains(@class,'alert-success')]"));

  private SelenideElement iconReturnToHome = $(By.xpath("//a[@title='Return to Home']"));

  @Step("Выбрать заголовок письма")
  public ContactUsPage selectSelectSubjectHeading(String option) {
    optionsSelectSubjectHeading.findBy(Condition.text(option)).click();
    return this;
  }

  @Step("Заполнить email адрес для отправки письма")
  public ContactUsPage fillInputEmail(String email) {
    inputEmail.clear();
    inputEmail.sendKeys(email);
    return this;
  }

  @Step("Загрузить файл")
  public ContactUsPage loadFile(String s) throws InterruptedException {
    File file = $(By.id("fileUpload")).uploadFile(new File("./src/test/resources/kvokka.jpg"));
   // driver.findElement(By.id("fileUpload")).sendKeys(new File("./src/test/resources/kvokka.jpg").getAbsolutePath());
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
    Assertions.assertAll(
            () -> successfulMessage.shouldBe(visible),
            () -> successfulMessage.shouldHave(text(successMessage))
    );
    return this;
  }

  @Step("Вернуться на главную страницу сайта")
  public MainPage returnToHome() {
    iconReturnToHome.click();
    return page(MainPage.class);
  }


}
