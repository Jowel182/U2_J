package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class DynamicControlsTest extends BaseTest {
    private final By DYNAMIC_CONTROL = By.xpath(String.format(PRECISE_TEXT_XPATH, "Dynamic Controls"));
    private final By ENABLE = By.xpath(String.format(PRECISE_TEXT_XPATH, "Enable"));
    private final By INPUT = By.xpath("//*[@id='input-example']//input");
    private final String RANDOM_TEXT = UUID.randomUUID().toString();

    @Test
    public void dynamicControlsTest() {
        driver.findElement(DYNAMIC_CONTROL).click();
        driver.findElement(ENABLE).click();
        WebElement inputField = driver.findElement(INPUT);
        Assert.assertTrue(isClickable(inputField), "Element wasn't enabled");
        inputField.sendKeys(RANDOM_TEXT);
        Assert.assertEquals(driver.findElement(INPUT).getAttribute("value"), RANDOM_TEXT,
                "Text is not displayed ");
        // todo: assert input is enabled
        // todo: input random generated text
        // todo: assert inputted text
    }

    private boolean isClickable(WebElement element){

        try{

            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.elementToBeClickable(element));

        }
        catch (TimeoutException exception){

            return false;

        }
        return true;

    }
}
