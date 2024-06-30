package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.AssertJUnit.assertTrue;

public class UI_Automation_POM {
    private WebDriver driver;
    private WebDriverWait wait;

    // WebElements or Locators for the login page
    public By input_Amount = By.cssSelector("#amount");
    public By label_Amount = By.cssSelector("label[for='amount']");
    public By dropdown_FromCurrency = By.xpath("//*[@id=\"midmarketFromCurrency\"]");
    public By input_FromCurrency = By.xpath("//*[@id=\"midmarketFromCurrency\"]/div[2]/div/input");
    public By input_ToCurrency = By.xpath("//*[@id=\"midmarketToCurrency\"]/div[2]/div/input");
    public By label_FromCurrency = By.cssSelector("label[for='midmarketFromCurrency']");
    public By dropdown_ToCurrency = By.xpath("//*[@id=\"midmarketToCurrency\"]");
    public By label_ToCurrency = By.cssSelector("label[for='midmarketToCurrency']");
    public By button_SwapCurrencies = By.cssSelector("button[aria-label='Swap currencies']");
    public By button_Convert = By.xpath("//button[text()='Convert']");
    public By textContainer_MidMarketRateInfo = By.cssSelector(".text-container");
    public By link_ViewSendRates = By.xpath("//p[contains(@class, 'sc-295edd9f-1')]");
    public By error_message = By.xpath("//div[@class='sc-4f0f6f94-1 xeMov']");

    public UI_Automation_POM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // waits up to 10 seconds
    }

    // Methods for common operations

    public HashMap<String, By> convertPageUI() {
        HashMap<String, By> convertPageUI = new HashMap<String, By>();
        convertPageUI.put("input_Amount", input_Amount);
        convertPageUI.put("label_Amount", label_Amount);
        convertPageUI.put("dropdown_FromCurrency", dropdown_FromCurrency);
        convertPageUI.put("label_FromCurrency", label_FromCurrency);
        convertPageUI.put("dropdown_ToCurrency", dropdown_ToCurrency);
        convertPageUI.put("label_ToCurrency", label_ToCurrency);
        convertPageUI.put("button_SwapCurrencies", button_SwapCurrencies);
        convertPageUI.put("button_Convert", button_Convert);
        convertPageUI.put("textContainer_MidMarketRateInfo", textContainer_MidMarketRateInfo);
        convertPageUI.put("link_ViewSendRates", link_ViewSendRates);
        convertPageUI.put("input_FromCurrency", input_FromCurrency);
        convertPageUI.put("input_ToCurrency", input_ToCurrency);
        convertPageUI.put("Result" , link_ViewSendRates);
        convertPageUI.put("error_Message" , error_message);
        return convertPageUI;
    }
    public void validateUIElements(HashMap<String, By> uiElements) {
        for (Map.Entry<String, By> entry : uiElements.entrySet()) {
            String elementName = entry.getKey();
            By locator = entry.getValue();
            try {
                assertTrue("Element not found: " + elementName, driver.findElement(locator).isDisplayed());
                System.out.println("Verified: " + elementName);
            } catch (Exception e) {
                System.err.println("Failed to verify element: " + elementName);
                e.printStackTrace();
            }
        }
    }
    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public void handleException(Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
        // Use any logger to log the error
    }

    public void sendKeys(Keys keys) {
        Actions actions = new Actions(driver);
        actions.sendKeys(keys).perform();
    }

    // Methods to interact with the login page


    }

