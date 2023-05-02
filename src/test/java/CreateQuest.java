import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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
import java.util.NoSuchElementException;

public class CreateQuest extends data{

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

    @Test //1
    public void createQuestWithoutData() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(2_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        List<WebElement> errorPrice = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorPrice.get(0).getText();
        Assert.assertEquals(text2, errorPriceMessage);

        WebElement errorSpecialization = driver.findElement(By.xpath("//p[@class='page__error']"));
        String text3 = errorSpecialization.getText();
        Assert.assertEquals(text3, specializationErrorMessage);

        List<WebElement> errorAddress = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorAddress.get(1).getText();
        Assert.assertEquals(text4, errorAddressMessage);

        List<WebElement> errorQuestTitle = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text5 = errorQuestTitle.get(2).getText();
        Assert.assertEquals(text5, errorQuestTitleMessage);

        List<WebElement> errorQuestDescription = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text6 = errorQuestDescription.get(3).getText();
        Assert.assertEquals(text6, errorQuestDescriptionMessage);

        WebElement errorCheckBox = driver.findElement(By.xpath("//div[@class='page__error']"));
        String text7 = errorCheckBox.getText();
        Assert.assertEquals(text7, errorCheckBoxMessage);

    }

    @Test //2
    public void createQuestWithPrice() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(2_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        WebElement errorSpecialization = driver.findElement(By.xpath("//p[@class='page__error']"));
        String text3 = errorSpecialization.getText();
        Assert.assertEquals(text3, specializationErrorMessage);

        List<WebElement> errorAddress = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorAddress.get(1).getText();
        Assert.assertEquals(text4, errorAddressMessage);

        List<WebElement> errorQuestTitle = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text5 = errorQuestTitle.get(2).getText();
        Assert.assertEquals(text5, errorQuestTitleMessage);

        List<WebElement> errorQuestDescription = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text6 = errorQuestDescription.get(3).getText();
        Assert.assertEquals(text6, errorQuestDescriptionMessage);

        WebElement errorCheckBox = driver.findElement(By.xpath("//div[@class='page__error']"));
        String text7 = errorCheckBox.getText();
        Assert.assertEquals(text7, errorCheckBoxMessage);
    }

    @Test //3
    public void createQuestWithNotValidPrice() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(notValidPrice);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(2_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        List<WebElement> errorPrice = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorPrice.get(0).getText();
        Assert.assertEquals(text2, errorMessageWhenPriceNotValid);
    }

    @Test //4
    public void createQuestWithPriceAndSpecialization() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);
    }

    @Test //5
    public void createQuestWithNoDataSpecialization() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(2_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        WebElement errorSpecialization = driver.findElement(By.xpath("//p[@class='page__error']"));
        String text3 = errorSpecialization.getText();
        Assert.assertEquals(text3, specializationErrorMessage);

        List<WebElement> errorAddress = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorAddress.get(1).getText();
        Assert.assertEquals(text4, errorAddressMessage);

        List<WebElement> errorQuestTitle = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text5 = errorQuestTitle.get(2).getText();
        Assert.assertEquals(text5, errorQuestTitleMessage);

        List<WebElement> errorQuestDescription = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text6 = errorQuestDescription.get(3).getText();
        Assert.assertEquals(text6, errorQuestDescriptionMessage);

        WebElement errorCheckBox = driver.findElement(By.xpath("//div[@class='page__error']"));
        String text7 = errorCheckBox.getText();
        Assert.assertEquals(text7, errorCheckBoxMessage);
    }

    @Test //6
    public void createQuestWhenAddDeleteSpecialization() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        WebElement removeSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn specialization__btn specialization__btn_remove']"));
        removeSpecializationButton.click();

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(2_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        WebElement errorSpecialization = driver.findElement(By.xpath("//p[@class='page__error']"));
        String text4 = errorSpecialization.getText();
        Assert.assertEquals(text4, specializationErrorMessage);
    }

    @Test //7
    public void findBarInSelectSpecialization(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecialization);

        WebElement selectWord = driver.findElement(By.xpath("//button[@class='dd__item']"));
        String text2 = selectWord.getText();
        selectWord.click();

        List<WebElement> checkSpecialization = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text3 = checkSpecialization.get(4).getText();

        Assert.assertEquals(text2, text3);
    }

    @Test //8
    public void findBarInSelectSpecializationNotValidData(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData);




//        ВЕРНУТЬСЯ К ТЕСТУ И ПРИДУМАТЬ ПРОВЕРКУ НА ТО ЧТО ПОИСК НИЧЕ НЕ НАШЕЛ

    }

    @Test //9
    public void findBarInSelectSpecializationNotValidData2(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData2);
    }

    @Test //10
    public void findBarInSelectSpecializationButWriteHalfWord(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationHalfWord);

        WebElement selectWord = driver.findElement(By.xpath("//button[@class='dd__item']"));
        String text2 = selectWord.getText();
        selectWord.click();

        List<WebElement> checkSpecialization = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text3 = checkSpecialization.get(4).getText();

        Assert.assertEquals(text2, text3);
    }

    @Test //11
    public void findBarInSelectSpecializationNotValidData3(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData3);
    }

    @Test //12
    public void findBarInSelectSpecializationNotValidData4(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData4);
    }

    @Test //13
    public void findBarInSelectSpecializationNotValidData5(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData5);
    }

    @Test //14
    public void findBarInSelectSpecializationNotValidData6(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData6);
    }

    @Test //15
    public void findBarInSkills() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSkills);

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);
    }

    @Test //16
    public void findBarInSkillsNotValidData() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData);
    }

    @Test //17
    public void findBarInSkillsNotValidData2() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecializationButNotValidData2);
    }

    @Test //18
    public void findBarInSkillsButWriteHalfWord() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSkillsHalfWord);

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);
    }

    @Test //19
    public void findBarInSkillsNotValidData3() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSkillsNotValidData3);
    }

    @Test //20
    public void findBarInSkillsNotValidData4() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSkillsNotValidData4);
    }

    @Test //21
    public void findBarInSkillsNotValidData5() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSkillsNotValidData5);
    }

    @Test //22
    public void findBarInSkillsNotValidData6() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSkillsNotValidData6);
    }

    @Test //23
    public void createQuestAdd2Specialization() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        WebElement againAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        againAddSpecializationButton.click();

        List<WebElement> againSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSpecializationBar.get(6).click();

        List<WebElement> againSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> againSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSkillsBar.get(7).click();

        List<WebElement> againSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSkills.get(0).click();

        List<WebElement> againCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text4 = againCheckSkillsBar.get(7).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> againCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text5 = againCheckFilter.get(1).getText();

        Assert.assertEquals(text5, text4);
    }

    @Test //24
    public void createQuestAddTwoSpecializationAndDeleteFirst() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        WebElement againAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        againAddSpecializationButton.click();

        List<WebElement> againSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSpecializationBar.get(6).click();

        List<WebElement> againSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> againSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSkillsBar.get(7).click();

        List<WebElement> againSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSkills.get(0).click();

        List<WebElement> againCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text4 = againCheckSkillsBar.get(7).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> againCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text5 = againCheckFilter.get(1).getText();

        Assert.assertEquals(text5, text4);

        List<WebElement> pressDeleteButton = driver.findElements(By.xpath("//button[@class='base-btn specialization__btn specialization__btn_remove']"));
        pressDeleteButton.get(0).click();

        WebElement checkLastFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text6 = checkLastFilter.getText();

        Assert.assertEquals(text6, text5);
    }

    @Test //25
    public void createQuestAddTwoSameSpecialization() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> inputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        inputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecialization);

        WebElement selectWord = driver.findElement(By.xpath("//button[@class='dd__item']"));
        String text2 = selectWord.getText();
        selectWord.click();

        List<WebElement> checkSpecialization = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text3 = checkSpecialization.get(4).getText();

        Assert.assertEquals(text2, text3);

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text4 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text5 = checkFilter.getText();

        Assert.assertEquals(text5, text4);

        WebElement againAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        againAddSpecializationButton.click();

        List<WebElement> againSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSpecializationBar.get(6).click();

        List<WebElement> againInputSomethingInFindBar = driver.findElements(By.xpath("//input[@step='any']"));
        againInputSomethingInFindBar.get(1).sendKeys(forFindBarInSelectSpecialization);
    }

    @Test //26
    public void createQuestAddThreeSpecialization() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        WebElement againAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        againAddSpecializationButton.click();

        List<WebElement> againSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSpecializationBar.get(6).click();

        List<WebElement> againSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> againSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSkillsBar.get(7).click();

        List<WebElement> againSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSkills.get(0).click();

        List<WebElement> againCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text4 = againCheckSkillsBar.get(7).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> againCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text5 = againCheckFilter.get(1).getText();

        Assert.assertEquals(text5, text4);

        WebElement oneMoreAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        oneMoreAddSpecializationButton.click();

        List<WebElement> oneMoreSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        oneMoreSpecializationBar.get(8).click();

        List<WebElement> oneMoreSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        oneMoreSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> oneMoreSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        oneMoreSkillsBar.get(9).click();

        List<WebElement> oneMoreSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        oneMoreSelectSkills.get(0).click();

        List<WebElement> oneMoreCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text6 = oneMoreCheckSkillsBar.get(9).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> oneMoreCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text7 = oneMoreCheckFilter.get(2).getText();

        Assert.assertEquals(text7, text6);
    }

    @Test //27
    public void createQuestAddMoreThenThreeSpecialization() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        WebElement againAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        againAddSpecializationButton.click();

        List<WebElement> againSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSpecializationBar.get(6).click();

        List<WebElement> againSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> againSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSkillsBar.get(7).click();

        List<WebElement> againSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSkills.get(0).click();

        List<WebElement> againCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text4 = againCheckSkillsBar.get(7).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> againCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text5 = againCheckFilter.get(1).getText();

        Assert.assertEquals(text5, text4);

        WebElement oneMoreAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        oneMoreAddSpecializationButton.click();

        List<WebElement> oneMoreSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        oneMoreSpecializationBar.get(8).click();

        List<WebElement> oneMoreSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        oneMoreSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> oneMoreSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        oneMoreSkillsBar.get(9).click();

        List<WebElement> oneMoreSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        oneMoreSelectSkills.get(0).click();

        List<WebElement> oneMoreCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text6 = oneMoreCheckSkillsBar.get(9).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> oneMoreCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text7 = oneMoreCheckFilter.get(2).getText();

        Assert.assertEquals(text7, text6);

        WebElement errorMessageInSpecialization = driver.findElement(By.xpath("//div[@class='skills__error']"));
        String text8 = errorMessageInSpecialization.getText();

        Assert.assertEquals(text8, specializationErrorMessage2);
    }

    @Test //28
    public void createQuestAddThreeSpecializationThenDeleteTwo() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        WebElement againAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        againAddSpecializationButton.click();

        List<WebElement> againSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSpecializationBar.get(6).click();

        List<WebElement> againSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> againSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        againSkillsBar.get(7).click();

        List<WebElement> againSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        againSelectSkills.get(0).click();

        List<WebElement> againCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text4 = againCheckSkillsBar.get(7).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> againCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text5 = againCheckFilter.get(1).getText();

        Assert.assertEquals(text5, text4);

        WebElement oneMoreAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        oneMoreAddSpecializationButton.click();

        List<WebElement> oneMoreSpecializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        oneMoreSpecializationBar.get(8).click();

        List<WebElement> oneMoreSelectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        oneMoreSelectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> oneMoreSkillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        oneMoreSkillsBar.get(9).click();

        List<WebElement> oneMoreSelectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        oneMoreSelectSkills.get(0).click();

        List<WebElement> oneMoreCheckSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text6 = oneMoreCheckSkillsBar.get(9).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        List<WebElement> oneMoreCheckFilter = driver.findElements(By.xpath("//div[@class='skill__badge']"));
        String text7 = oneMoreCheckFilter.get(2).getText();
        System.out.println(text7);

        Assert.assertEquals(text7, text6);

        List<WebElement> deleteFirstSpecialization = driver.findElements(By.xpath("//button[@class='base-btn specialization__btn specialization__btn_remove']"));
        deleteFirstSpecialization.get(0).click();

        List<WebElement> deleteSecondSpecialization = driver.findElements(By.xpath("//button[@class='base-btn specialization__btn specialization__btn_remove']"));
        deleteSecondSpecialization.get(0).click();

        WebElement checkLastFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text8 = checkLastFilter.getText();

        Assert.assertEquals(text8, text7);
    }

    @Test //29
    public void createQuestAddress() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();
    }

    @Test //30
    public void createQuestNotValidAddress() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(notValidAddress);

        List<WebElement> errorMessageAddress = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorMessageAddress.get(1).getText();

        Assert.assertEquals(text4, errorAddressMessage2);
    }

    @Test //31
    public void createQuestNotValidAddress2() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(notValidAddress2);

        List<WebElement> errorMessageAddress = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorMessageAddress.get(1).getText();

        Assert.assertEquals(text4, errorAddressMessage2);
    }

    @Test //32
    public void createQuestWrite251Characters() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(textForQuestTitle251Characters);

        List<WebElement> errorMessageQuestTitle = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorMessageQuestTitle.get(2).getText();

        Assert.assertEquals(text4, errorMessageQuestTitle250Characters);
    }

    @Test //33
    public void createQuestWriteTwoSpaces() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(errorMessageQuestTitleWithTwoSpaces);

        List<WebElement> errorMessageQuestTitle = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorMessageQuestTitle.get(2).getText();

        Assert.assertEquals(text4, errorQuestTitleMessage);
    }

    @Test //34
    public void createQuestWriteOneLetter() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(errorMessageQuestTitleWithOneLetter);

        List<WebElement> errorMessageQuestTitle = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = errorMessageQuestTitle.get(2).getText();
        System.out.println(text4);

        Assert.assertEquals(text4, errorMessageQuestTitleWhenOneLetter);
    }

    @Test //35
    public void createQuestWrite2001Characters() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(validMessageForQuestTitle);

        WebElement inputCharactersInQuestDescription = driver.findElement(By.xpath("//textarea[@data-selector='BASE-TEXTAREA-TEXTAREA']"));
        inputCharactersInQuestDescription.sendKeys(textForQuestDescription2001Characters);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-field__err']")));

        List<WebElement> checkErrorMessageQuestDescription = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = checkErrorMessageQuestDescription.get(3).getText();

        Assert.assertEquals(text4, errorMessageQuestDescriptionWhen2001Characters);
    }

    @Test //36
    public void createQuestWrite5charactersInDescription() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(validMessageForQuestTitle);

        WebElement inputCharactersInQuestDescription = driver.findElement(By.xpath("//textarea[@data-selector='BASE-TEXTAREA-TEXTAREA']"));
        inputCharactersInQuestDescription.sendKeys(errorMessageQuestDescriptionWrite5Characters);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-field__err']")));

        List<WebElement> checkErrorMessageQuestDescription = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text4 = checkErrorMessageQuestDescription.get(3).getText();

        Assert.assertEquals(text4, errorMessageQuestDescriptionWhen5Characters);
    }

    @Test //37
    public void createQuestCheckBox() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(validMessageForQuestTitle);

        WebElement inputQuestDescription = driver.findElement(By.xpath("//textarea[@data-selector='BASE-TEXTAREA-TEXTAREA']"));
        inputQuestDescription.sendKeys(validTextForQuestDescription);

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(2_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        Actions builder2 = new Actions(driver);
        Action keyDownPressed2 = builder2.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed2.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        WebElement errorMessageCheckbox = driver.findElement(By.xpath("//div[@class='page__error']"));
        String text4 = errorMessageCheckbox.getText();

        Assert.assertEquals(text4, errorCheckBoxMessage);
    }

    @Test //38
    public void createQuestValidCase() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click(); /////////////////////////////////

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();
        String textForCheckFilter = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(validMessageForQuestTitle);

        WebElement inputQuestDescription = driver.findElement(By.xpath("//textarea[@data-selector='BASE-TEXTAREA-TEXTAREA']"));
        inputQuestDescription.sendKeys(validTextForQuestDescription);

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(1_000);

        WebElement clickCheckBox = driver.findElement(By.xpath("//input[@id='understand']"));
        clickCheckBox.click();

        Thread.sleep(1_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__box info']")));

        WebElement confirmButton = driver.findElement(By.xpath("//button[@class='base-btn buttons__button']"));
        confirmButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__box status']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn status__btn']")));

        WebElement okButton = driver.findElement(By.xpath("//button[@class='base-btn status__btn']"));
        okButton.click();

        WebElement addressAfterCreate = driver.findElement(By.xpath("//div[@class='quest__address']"));
        String text5 = addressAfterCreate.getText();

        Assert.assertEquals(text5, address);

        WebElement filterAfterCreate = driver.findElement(By.xpath("//li[@class='skills__item skills__item_blue']"));
        String text4 = filterAfterCreate.getText();

        Assert.assertEquals(text4, textForCheckFilter);

        WebElement questTitleAfterCreate = driver.findElement(By.xpath("//h2[@class='quest__title']"));
        String text6 = questTitleAfterCreate.getText();

        Assert.assertEquals(text6, validMessageForQuestTitle);

        WebElement questDescription = driver.findElement(By.xpath("//span[@class='quest__description']"));
        String text7 = questDescription.getText();

        Assert.assertEquals(text7, validTextForQuestDescription);
    }

    @Test //39
    public void createQuestWithDifferentDataInHeader() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> runTimeBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        runTimeBar.get(0).click();

        List<WebElement> selectRunTime = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectRunTime.get(1).click();

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        List<WebElement> employmentBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        employmentBar.get(1).click();

        List<WebElement> selectEmployment = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectEmployment.get(3).click();

        List<WebElement> remoteWorkBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        remoteWorkBar.get(2).click();

        Thread.sleep(1_000);

        List<WebElement> selectRemoteWork = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectRemoteWork.get(1).click();

        List<WebElement> payPeriodBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        payPeriodBar.get(3).click();

        List<WebElement> selectPayPeriod = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectPayPeriod.get(8).click();



        List<WebElement> checkRunTime = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String textRunTime = checkRunTime.get(0).getText();

        List<WebElement> checkPayPeriod = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String textPayPeriod = checkPayPeriod.get(3).getText();









        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        Thread.sleep(1_000);

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(1_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        Thread.sleep(1_000);

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();
        String textForCheckFilter = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(validMessageForQuestTitle);

        WebElement inputQuestDescription = driver.findElement(By.xpath("//textarea[@data-selector='BASE-TEXTAREA-TEXTAREA']"));
        inputQuestDescription.sendKeys(validTextForQuestDescription);

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(1_000);

        WebElement clickCheckBox = driver.findElement(By.xpath("//input[@id='understand']"));
        clickCheckBox.click();

        Thread.sleep(1_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__box info']")));

        WebElement confirmButton = driver.findElement(By.xpath("//button[@class='base-btn buttons__button']"));
        confirmButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__box status']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn status__btn']")));

        WebElement okButton = driver.findElement(By.xpath("//button[@class='base-btn status__btn']"));
        okButton.click();

        WebElement filterAfterCreate = driver.findElement(By.xpath("//li[@class='skills__item skills__item_blue']"));
        String text4 = filterAfterCreate.getText();

        Assert.assertEquals(text4, textForCheckFilter);

        WebElement checkPayPeriodAfterCreate = driver.findElement(By.xpath("//div[@class='worker-data__payPeriod']"));
        String payPeriodAfterCreate = checkPayPeriodAfterCreate.getText();

        Assert.assertEquals(payPeriodAfterCreate, textPayPeriod);

        WebElement checkRunTimeAfterCreate = driver.findElement(By.xpath("//div[@class='worker-data__priority-title worker-data__priority-title_normal']"));
        String runTimeAfterCreate = checkRunTimeAfterCreate.getText();

        Assert.assertEquals(runTimeAfterCreate, textRunTime);
    }

    @Test //40
    public void createQuestAddTwoSpecializationButOneEmpty() throws InterruptedException {
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

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

        WebElement createQuestButton = driver.findElement(By.xpath("//button[@class='base-btn header__btn']"));
        createQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page__title']")));

        List<WebElement> inputPrice = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputPrice.get(0).sendKeys(validPrice);

        WebElement addSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        addSpecializationButton.click();

        List<WebElement> specializationBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        specializationBar.get(4).click();

        List<WebElement> selectSpecialization = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSpecialization.get(0).click();

        Thread.sleep(2_000);

        List<WebElement> skillsBar = driver.findElements(By.xpath("//span[@class='icon-caret_down dd__caret']"));
        skillsBar.get(5).click();

        List<WebElement> selectSkills = driver.findElements(By.xpath("//button[@class='dd__item']"));
        selectSkills.get(0).click();

        List<WebElement> checkSkillsBar = driver.findElements(By.xpath("//span[@class='dd__title']"));
        String text2 = checkSkillsBar.get(5).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='skill__badge']")));

        WebElement checkFilter = driver.findElement(By.xpath("//div[@class='skill__badge']"));
        String text3 = checkFilter.getText();
        String textForCheckFilter = checkFilter.getText();

        Assert.assertEquals(text3, text2);

        WebElement againAddSpecializationButton = driver.findElement(By.xpath("//button[@class='base-btn skills__btn-add']"));
        againAddSpecializationButton.click();

        Thread.sleep(1_000);

        List<WebElement> inputAddress = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputAddress.get(1).sendKeys(address);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selector__item']")));

        List<WebElement> selectAddress = driver.findElements(By.xpath("//div[@class='selector__item']"));
        selectAddress.get(3).click();

        List<WebElement> inputQuestTitle = driver.findElements(By.xpath("//input[@class='ctm-field__input']"));
        inputQuestTitle.get(2).sendKeys(validMessageForQuestTitle);

        WebElement inputQuestDescription = driver.findElement(By.xpath("//textarea[@data-selector='BASE-TEXTAREA-TEXTAREA']"));
        inputQuestDescription.sendKeys(validTextForQuestDescription);

        Actions builder = new Actions(driver);
        Action keyDownPressed = builder.sendKeys(Keys.PAGE_DOWN).build();
        keyDownPressed.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__bottom']")));

        Thread.sleep(1_000);

        WebElement clickCheckBox = driver.findElement(By.xpath("//input[@id='understand']"));
        clickCheckBox.click();

        Thread.sleep(1_000);

        WebElement pressCreateQuestButton = driver.findElement(By.xpath("//div[@class='btn__create']"));
        pressCreateQuestButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__box info']")));

        WebElement confirmButton = driver.findElement(By.xpath("//button[@class='base-btn buttons__button']"));
        confirmButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ctm-modal__box status']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='base-btn status__btn']")));

        WebElement okButton = driver.findElement(By.xpath("//button[@class='base-btn status__btn']"));
        okButton.click();

        WebElement filterAfterCreate = driver.findElement(By.xpath("//li[@class='skills__item skills__item_blue']"));
        String text4 = filterAfterCreate.getText();

        Assert.assertEquals(text4, textForCheckFilter);
    }



}
