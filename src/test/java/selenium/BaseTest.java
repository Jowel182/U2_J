package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected final String URL = "https://www.wikipedia.org/";

    protected final String
            PRECISE_TEXT_XPATH = "//*[text()='%s']";
    protected final String PARTICULAR_TEXT_XPATH = "//*[contains(text(),'%s')]";
    protected final String RELATIVE_RESOURCE_PATH = "C:/Users/Lenovo/Download";


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }



    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
