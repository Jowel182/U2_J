package selenium;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.*;
import static org.awaitility.Duration.*;
import static java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class WikiTest extends BaseTest{

    private final By INPUT = By.xpath("//input[@id='searchInput']");
    private final By SEARCHB = By.xpath("//button[@type='submit']");
    private final String ALBERT = "Albert Einstein";
    private final By TOOLS = By.xpath("//input[@id='vector-page-tools-dropdown-checkbox']");
    private final By DOWNLOAD_OP = By.xpath("//span[normalize-space()='Download as PDF']");
    private final By DOWNLOAD_B = By.xpath("//button[@type='submit']");
    private final By FILE_ELEMENT = By.xpath("//div[@class='mw-electronpdfservice-selection-label-desc']");
    private final String FILE_NAME = driver.findElement(FILE_ELEMENT).getAttribute("textContent");
    private final String PATH = RELATIVE_RESOURCE_PATH + FILE_NAME ;
    private final File downloadedFile = new File(PATH);

    @Test
    public void WikiT(){

        WebElement inputField = driver.findElement(INPUT);
        inputField.sendKeys(ALBERT);

        Assert.assertEquals(driver.findElement(INPUT).getAttribute("value"), ALBERT, "Text is not displayed");

        driver.findElement(SEARCHB).click();

        driver.findElement(TOOLS).click();
        driver.findElement(DOWNLOAD_OP).click();

        driver.findElement(DOWNLOAD_B).click();

        Assert.assertTrue(fileExists(downloadedFile), "File is not downloaded.");

    }

    private boolean fileExists(File file){

        try {

            Awaitility.await().atMost(40, SECONDS).until(file::exists);

        }
        catch (ConditionTimeoutException exception){

            return false;

        }
        return true;

    }


}
