package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import POM.UI_Automation_POM;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.lang.reflect.Field;
import java.util.HashMap;

public class UI_Automation {
    private WebDriver driver;
    private UI_Automation_POM convertPage;

    @Given("I am on the xe landing page")
    public void navigateToconvertPage() {
        // Setup WebDriver and navigate to the login page
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.xe.com/");
        convertPage = new UI_Automation_POM(driver);
    }

    @When("Verified the URL page is {string}")
    public void validateUrlLink(String url) {
        // Validate the url link
        String actualUrl = driver.getCurrentUrl();
        assertEquals(url, actualUrl);

    }

    @Then("Verified convert module UI of the page")
    public void verifiedConvertUIPage() {
        // verified the convert module UI is displayed
        HashMap<String, By> uiElements = convertPage.convertPageUI();
        convertPage.validateUIElements(uiElements);
    }

    @When("I enter {string} in the {string} field")
    public void enterAmount(String amount, String fieldName) {
        By locator = convertPage.convertPageUI().get("input_" + fieldName);
        convertPage.sendKeys(locator, amount);
    }

    @When("I select {string} and type {string} to the field")
    public void selectCurrency(String fieldName , String currency) throws InterruptedException {
        By dropdownLocator = convertPage.convertPageUI().get("dropdown_" + fieldName);
        By inputLocator = convertPage.convertPageUI().get("input_" + fieldName);
        convertPage.click(dropdownLocator);
        convertPage.sendKeys(inputLocator, currency);
        Thread.sleep(1000);
        convertPage.sendKeys(Keys.ENTER);
    }

    @When("I click on the {string} button")
    public void clickButton(String buttonName) {
        By locator = convertPage.convertPageUI().get("button_" + buttonName);
        convertPage.click(locator);
    }

    @Then("I should see the converted amount in the {string} field")
    public void verifyConvertedAmount(String fieldName) throws InterruptedException {
        By locator = convertPage.convertPageUI().get(fieldName);
        Thread.sleep(2000);
        String convertedAmount = convertPage.getText(locator);
        assertNotNull(convertedAmount);
        assertFalse(convertedAmount.isEmpty());
        System.out.println( "The convert amount is " + convertedAmount);
    }

    @When("I check {string} button is available or not")
    public void verifyButtonStatus(String buttonName){
        By locator = convertPage.convertPageUI().get("button_" + buttonName);
        WebElement convertButton = driver.findElement(locator);
        boolean isDisabled = !convertButton.isEnabled();  // Check if the button is disabled
        System.out.println("Button is disabled: " + isDisabled);
    }

    @Then("I check the {string} message")
    public void verifyErrorMessage(String messageType){
        By locator = convertPage.convertPageUI().get(messageType + "_Message");
        String errorMessage = convertPage.getText(locator);
        System.out.println("The error message show :" + errorMessage);
    }

    @And("I check back the {string} field value for correction value")
    public void checkCorrectedAmount(String amountField){
        By locator = convertPage.convertPageUI().get("input_" + amountField);
        WebElement inputField = driver.findElement(locator);
        String changedAmount = inputField.getAttribute("value");
        System.out.println("The correction Amount is: " + changedAmount);
    }

    // Cleanup after each scenario

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
