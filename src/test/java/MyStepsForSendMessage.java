import com.codeborne.selenide.Selenide;
import com.geekbrains.lesson8.ContactUsPage;
import com.geekbrains.lesson8.LoginPage;
import com.geekbrains.lesson8.MyAccountPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class MyStepsForSendMessage {
  @Given("^User Authorized2$")
  public void userAuthorized() {
    open("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    new LoginPage().login("spartakex93@test.test", "123456");
  }

  @When("^click link contact us$")
  public void clickLinkContactUs() {
    new MyAccountPage().navigationBlock.clickContactUs();
  }

  @And("^select subject heading$")
  public void selectSubjectHeading() {
    new ContactUsPage().selectSelectSubjectHeading("Customer service");
  }

  @And("fill input email {string}")
  public void fillInputEmailEmail(String email) {
    new ContactUsPage().fillInputEmail(email);
  }

  @And("^fill message$")
  public void fillMessage() {
    new ContactUsPage().fillMessage("123");
  }

 /* @And("^load file$")
  public void loadFile() throws InterruptedException {
    new ContactUsPage().loadFile("./src/test/resources/kvokka.jpg");
  }*/

  @And("^click button send message$")
  public void clickButtonSendMessage() {
    new ContactUsPage().sendMessage();
  }

  @Then("^check success send message$")
  public void checkSuccessSendMessage() {
    new ContactUsPage().checkSuccessSendMessage("Your message has been successfully sent to our team.");
  }

  @After(value = "@close")
  public void quitBrowser() {
    Selenide.closeWebDriver();
  }

}
