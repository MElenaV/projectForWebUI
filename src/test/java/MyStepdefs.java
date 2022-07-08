import com.codeborne.selenide.Selenide;
import com.geekbrains.lesson8.LoginPage;
import com.geekbrains.lesson8.MyAccountPage;
import com.geekbrains.lesson8.SuccessAddToCartPopup;
import com.geekbrains.lesson8.TShirtsPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {
    @Given("^User Authorized$")
    public void userAuthorized() {
        open("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        new LoginPage().login("spartakex93@test.test", "123456");
    }

    @When("^click tshirts button in women submenu$")
    public void clickTshirtsButtonInWomenSubmenu() {
        new MyAccountPage().navigationBlock.clickTShirtsButtonInWomenSubmenu();
    }

    @And("^select size s$")
    public void selectSizeS() {
        new TShirtsPage().selectSize("S");
    }

    @And("select price")
    public void selectPrice() {
        new TShirtsPage().selectPrice(100);
    }

    @And("^click tshirt cart by name$")
    public void clickTshirtCartByName() {
        new TShirtsPage().addTShirtToCartByName("Faded");
    }

    @Then("^check total sum$")
    public void checkTotalSum() {
        new SuccessAddToCartPopup().checkTotalSumm("$18.51");
    }

    @And("select size {string}")
    public void selectSizeSize(String size) {
        new TShirtsPage().selectSize(size);
    }

    @After(value = "@close")
    public void quitBrowser() {
        Selenide.closeWebDriver();
    }
}
