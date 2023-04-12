import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckBlock extends data{

    private ChromeDriver driver;
    private WebDriverWait wait;



    @BeforeClass
    public static void configure() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
    }

//    "D:\chromedriver.exe"

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://explorer.workquest.co/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void shutDown() {
        driver.close();
    }

    @Test
    public void Check(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//caption[@id='__BVID__92__caption_']")));

        List<WebElement> openAllBlocks = driver.findElements(By.xpath("//a[@href='/blocks']"));
        openAllBlocks.get(1).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//caption[@id='__BVID__155__caption_']")));

        List<WebElement> check = driver.findElements(By.xpath("//td[@aria-colindex='2']"));
        String text = check.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, textForCheckBlock);





    }
}
