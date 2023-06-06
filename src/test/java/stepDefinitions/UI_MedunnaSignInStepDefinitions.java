package stepDefinitions;

import io.cucumber.java.en.*;
import pages.MedunnaHomePage;
import pages.MedunnaLoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class UI_MedunnaSignInStepDefinitions {

    MedunnaHomePage medunnaHomePage=new MedunnaHomePage();
    MedunnaLoginPage medunnaLoginPage=new MedunnaLoginPage();

    @Given("go to {string}")
    public void go_to(String url) {
        Driver.getDriver().get(ConfigReader.getProperty("url"));

    }
    @When("click on user icon")
    public void click_on_user_icon() {
    medunnaHomePage.userIcon.click();

    }
    @When("click on sign in option")
    public void click_on_sign_in_option() {

    medunnaHomePage.signInOption.click();
    }
    @When("send username into username input")
    public void send_username_into_username_input() {

    medunnaLoginPage.usernameInput.sendKeys(ConfigReader.getProperty("medunnaUsername"));
    }
    @When("send password into password input")
    public void send_password_into_password_input() {
    medunnaLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("medunndaPassword"));

    }
    @When("click on sign in submit button")
    public void click_on_sign_in_sbumit_button() {
    medunnaLoginPage.signInSubmitButton.click();

    }


}
