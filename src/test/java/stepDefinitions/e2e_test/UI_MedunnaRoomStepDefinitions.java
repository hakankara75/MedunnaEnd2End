package stepDefinitions.e2e_test;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MedunnaHomePage;
import pages.MedunnaRoomPage;
import utilities.Driver;

import java.time.Duration;

public class UI_MedunnaRoomStepDefinitions {

    MedunnaHomePage medunnaHomePage=new MedunnaHomePage();
    MedunnaRoomPage medunnaRoomPage= new MedunnaRoomPage();
    public static int roomNumberFaker;
    public static String roomId;

    @When("Click on ItemsAndTitles")
    public void clickOnItemsAndTitles() {
        medunnaHomePage.itemsdAndTitles.click();
    }


    @When("click on Room option")
    public void click_on_room_option() {
        medunnaHomePage.roomOption.click();

    }
    @When("click on Create a new room button")
    public void click_on_create_a_new_room_button() {
        medunnaRoomPage.createANewRoomButton.click();

    }
    @When("enter {string} room number input")
    public void enter_room_number_input(String roomNumber) {
    roomNumberFaker=Faker.instance().number().numberBetween(100000, 1000000);
        medunnaRoomPage.roomNumberInput.sendKeys(roomNumberFaker+"" );

    }
    @When("select Suite option from Room Type dropdown")
    public void select_suite_option_from_room_type_dropdown() {
        new Select(medunnaRoomPage.roomTypeDropDown).selectByIndex(2);

    }
    @When("click on Status checkbox")
    public void click_on_status_checkbox() {
        medunnaRoomPage.statusCheckbox.click();

    }
    @When("enter {string} in Price input")
    public void enter_in_price_input(String price) {
        medunnaRoomPage.priceInput.sendKeys(price);

    }
    @When("enter {string} in Description input")
    public void enter_in_description_input(String description) {
        medunnaRoomPage.descriptionInput.sendKeys(description);

    }
    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
//        WebElement saveSubmitButton= medunnaRoomPage.saveSubmitButton;
//        wait.until(ExpectedConditions.visibilityOf(saveSubmitButton));
//        saveSubmitButton.click();
        WebElement saveSubmitButton = Driver.getDriver().findElement(By.id("save-entity"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
        jsExecutor.executeScript("arguments[0].click();", saveSubmitButton);
        Thread.sleep(1000);
        roomId = medunnaRoomPage.alert.getText().replaceAll("[^0-9]","");

    }
    @When("close the application")
    public void close_the_application() {
        Driver.closeDriver();

    }


}
