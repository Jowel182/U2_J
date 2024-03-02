package selenium;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import static java.util.concurrent.TimeUnit.*;

public class WikiTest extends BaseTest{

    private final By INPUT = By.xpath("//input[@id='searchInput']");
    private final By SEARCHB = By.xpath("//button[@type='submit']");
    private final String ALBERT = "Albert Einstein";
    private final By TOOLS = By.xpath("//input[@id='vector-page-tools-dropdown-checkbox']");
    private final By DOWNLOAD_OP = By.xpath("//span[normalize-space()='Descargar como PDF']");
    private final By DOWNLOAD_B = By.xpath("//button[@type='submit']");



    @Test
    public void WikiT(){


        WebElement inputField = driver.findElement(INPUT);
        inputField.sendKeys(ALBERT);

        Assert.assertEquals(driver.findElement(INPUT).getAttribute("value"), ALBERT, "Text is not displayed");

        driver.findElement(SEARCHB).click();

        driver.findElement(TOOLS).click();

        driver.findElement(DOWNLOAD_OP).click();
        driver.findElement(DOWNLOAD_B).click();


        WebElement PDFNameElement = driver.findElement(By.className("mw-electronpdfservice-selection-label-desc"));
        String fileName = PDFNameElement.getText();
        String path = RELATIVE_RESOURCE_PATH + fileName;
        File downloadedFile = new File(path);

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


    @AfterMethod
    public void deleteFile(){

        WebElement PDFNameElement = driver.findElement(By.className("mw-electronpdfservice-selection-label-desc"));
        String fileName = PDFNameElement.getText();
        String path = RELATIVE_RESOURCE_PATH + fileName;
        File downloadedFile = new File(path);

        if (downloadedFile.exists()){

            downloadedFile.delete();

        }

    }

}
