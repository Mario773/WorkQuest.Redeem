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

import java.nio.channels.WritableByteChannel;
import java.time.Duration;
import java.util.List;

public class RedeemButton extends data{

    private ChromeDriver driver;
    private WebDriverWait wait;



    @BeforeClass
    public static void configure() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
    }


    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://testnet-app.workquest.co");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void shutDown() {
        driver.close();
    }


    @Test
    public void redeemButton() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(notValidLogin); /////

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(password);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='wallet__text wallet__text_title']")));

        WebElement checkPage = driver.findElement(By.xpath("//div[@class='wallet__text wallet__text_title']"));
        String text = checkPage.getText();
        Assert.assertEquals(text, importWalletPage);

        WebElement inputSecretPhrase = driver.findElement(By.xpath("//input[@step='any']"));
        inputSecretPhrase.sendKeys(mnemonic);

        WebElement pressImportWalletButton = driver.findElement(By.xpath("//div[@class='wallet__action']"));
        pressImportWalletButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__box ctm-modal-download ctm-modal-download-desktop']")));

        WebElement closePopUp = driver.findElement(By.xpath("//button[@class='ctm-modal__x']"));
        closePopUp.click();

        WebElement deFiFunctionalityButton = driver.findElement(By.xpath("//div[@class='header__link header__link_menu']"));
        deFiFunctionalityButton.click();

        List<WebElement> openBridge = driver.findElements(By.xpath("//div[@class='menu__text menu__text_header']"));
        openBridge.get(6).click();

        String text2 = driver.getCurrentUrl();
        Assert.assertEquals(text2, bridgePage);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='info-block__name']")));

        List<WebElement> setSourceBlockchain = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        setSourceBlockchain.get(0).click();

        List<WebElement> setWorkQuest = driver.findElements(By.xpath("//button[@class='dd__item dd__item_icon']"));
        setWorkQuest.get(2).click();

        WebElement createSwapButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        createSwapButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__header']")));

        WebElement inputAmount = driver.findElement(By.xpath("//input[@class='ctm-field__input ctm-field__input_padding-r']"));
        inputAmount.sendKeys("5");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        List<WebElement> pressCreateButton = driver.findElements(By.xpath("//button[@class='base-btn']"));
        pressCreateButton.get(1).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__header']")));

        WebElement pressConfirmButton = driver.findElement(By.xpath("//button[@class='base-btn buttons__button']"));
        pressConfirmButton.click();

        Thread.sleep(1_000);

        WebElement pressConfirmButtonSecondButton = driver.findElement(By.xpath("//button[@class='base-btn buttons__button']"));
        pressConfirmButtonSecondButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='status__content']")));

        WebElement closeNotificationAboutSuccessfully = driver.findElement(By.xpath("//button[@class='base-btn status__btn']"));
        closeNotificationAboutSuccessfully.click();

        List<WebElement> checkButton = driver.findElements(By.xpath("//button[@type='submit']"));
        String text3 = checkButton.get(3).getText();
        if (text3.equals(redeemButton)){
            System.out.println("OOOOKKKKKKKKKKKKKKKKKKK");
        }else{
            throw new IllegalArgumentException("ERROR, button is " + wrongButtonRedeem);
        }
    }


}
