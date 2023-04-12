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

public class LogIn extends data{

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
    public void logIn(){
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

        String text2 = driver.getCurrentUrl();

        Assert.assertEquals(text2, pageAfterLogIn);
    }

    @Test //2
    public void logInWhenNotValidLogin(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(notValidLogin);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(password);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-body custom-toast-width']")));
    }

    @Test //3
    public void logInWhenNotValidPassword(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(notValidPassword);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-body custom-toast-width']")));
    }

    @Test //4
    public void logInWithoutLogin(){
        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(password);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        List<WebElement> errorMessage1 = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = errorMessage1.get(0).getText();
        
        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorMessage);
    }

    @Test //5
    public void logInWithoutPassword(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        List<WebElement> errorMessage2_1 = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = errorMessage2_1.get(1).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorMessage2);
    }

    @Test //6
    public void logInWithSpaceInLogin(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(loginWithSpace);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(password);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        List<WebElement> errorMessage3_1 = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = errorMessage3_1.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorMessage3);
    }

    @Test //7
    public void logInWithSpaceInPassword(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(login);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(passwordWithSpace);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-body custom-toast-width']")));
    }

    @Test //8
    public void logInWhenLoginOnlyWithNumbers(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(loginWithNumbersOnly);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(password);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        List<WebElement> errorMessage3_1 = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = errorMessage3_1.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorMessage3);
    }

    @Test //9
    public void logInWhenLoginWithoutLastPart(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(loginWithoutLastPart);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(password);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        List<WebElement> errorMessage3_1 = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = errorMessage3_1.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorMessage3);
    }

    @Test //10
    public void logInWhenLoginWithoutEt(){
        List<WebElement> inputLogin = driver.findElements(By.xpath("//input[@step='any']"));
        inputLogin.get(0).sendKeys(loginWithoutEt);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(1).sendKeys(password);

        WebElement pressLoginButton = driver.findElement(By.xpath("//button[@class='base-btn']"));
        pressLoginButton.click();

        List<WebElement> errorMessage3_1 = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = errorMessage3_1.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorMessage3);
    }

    @Test //11
    public void logInSecretPhraseWithOnlyNumber(){
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
        inputSecretPhrase.sendKeys(secretPhraseOnlyWithNumber);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorMessage.getText();

        Assert.assertEquals(text2, errorMessageInSecretPhrase);
    }

    @Test //12
    public void logInSecretPhraseWithOnlyOneWord(){
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
        inputSecretPhrase.sendKeys(secretPhraseOnlyWithOneWord);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorMessage.getText();

        Assert.assertEquals(text2, errorMessageInSecretPhrase);
    }

    @Test //13
    public void logInSecretPhraseWithSpaceOnly(){
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
        inputSecretPhrase.sendKeys(secretPhraseWithSpaceOnly);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorMessage.getText();

        Assert.assertEquals(text2, errorMessageInSecretPhrase2);
    }

    @Test //14
    public void logInValidSecretPhraseButWithoutSpaces(){
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
        inputSecretPhrase.sendKeys(secretPhraseWithoutSpaces);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorMessage.getText();

        Assert.assertEquals(text2, errorMessageInSecretPhrase);
    }

    @Test //15
    public void logInValidSecretPhraseButWithSpaceInEnd(){
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
        inputSecretPhrase.sendKeys(secretPhraseWithSpaceInEnd);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorMessage.getText();

        Assert.assertEquals(text2, errorMessageInSecretPhrase);
    }

    @Test //16
    public void logInSecretPhraseButFirstLetterBig(){
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
        inputSecretPhrase.sendKeys(secretPhraseWithBigFirstLetter);

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ctm-field__err']"));
        String text2 = errorMessage.getText();

        Assert.assertEquals(text2, errorMessageInSecretPhrase);
    }

    @Test //17
    public void write1InFirstName(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(notValidFieldForRegistration);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, firstNameErrorMessage);
    }

    @Test //18
    public void write1InLastName(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(notValidFieldForRegistration);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(1).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, lastNameErrorMessage);
    }

    @Test //19
    public void write1InEmail(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(notValidFieldForRegistration);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(2).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, emailErrorMessage);
    }

    @Test //20
    public void writeEmailWithoutEt(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(loginWithoutEt);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(2).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, emailErrorMessage);
    }

    @Test //21
    public void writeEmailWithoutDot(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(emailWithoutDot);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(2).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, emailErrorMessage);
    }

    @Test //22
    public void writeSpaceAfterEmail(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(loginWithSpace);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(2).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, emailErrorMessage);
    }

    @Test //23
    public void writeEmailWithSpaceOnStart(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(emailWithSpaceOnStart);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(2).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, emailErrorMessage);
    }

    @Test //24
    public void writeFirstNameWithSpace(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstNameWithSpaceInEnd);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, firstNameErrorMessage);
    }

    @Test //25
    public void writeTwoWordsInFirstName(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstNameWithTwoWords);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(0).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, firstNameErrorMessage);
    }

    @Test //26
    public void lastNameWithSpace(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastNameWithSpaceInEnd);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(1).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, lastNameErrorMessage);
    }

    @Test //27
    public void writeTwoWordsInLastName(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastNameWithTwoWords);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(1).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, lastNameErrorMessage);
    }

    @Test //28
    public void write1InPassword(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(notValidFieldForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessage8Characters);
    }

    @Test //29
    public void writePasswordWithSmallLetters(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordWithSmallLetters);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageSmallLetters);
    }

    @Test //30
    public void writePasswordWithBigLetter(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordWithOneBigLetter);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageWith1Number);
    }

    @Test //31
    public void writePasswordWithOneBigLetterOneSmallLetterOneNumber(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordWithOneBigLetterSmallLetterOneNumber);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        System.out.println(res);
        Assert.assertEquals(res, errorPasswordMessageWithOneSpecialCharacter);
    }

    @Test //32
    public void writePasswordWithOneBigLetterOneSmallLetterOneSpecialCharacter() {
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();
        if (text.isEmpty()){
            System.out.println("OK");
        }else{
            throw new IllegalArgumentException("ERROR");
        }
    }

    @Test //33
    public void writePasswordWithSpaceInEnd(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordWithSpaceInEnd);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageWithSpace);
    }

    @Test //34
    public void writePasswordWithSpaceOnStart(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordWithSpaceOnStart);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageWithSpace);
    }

    @Test //35
    public void writePasswordButSpaceBetweenWords(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordWithSpaceBetweenWords);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageWithSpace);
    }

    @Test //36
    public void writePasswordButAllLettersBig(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordWithOnlyBigLetters);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(3).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageWhenAllLettersBig);
    }

    @Test //37
    public void secondPasswordNotValid(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(notValidSecondPassword);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(4).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageAboutSecondPassword);
    }

    @Test //38
    public void writeSecondPasswordButSpaceOnStart(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordWithSpaceOnStart);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(4).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageAboutSecondPassword);
    }

    @Test //39
    public void writeSecondPasswordButSpaceInEnd(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(email);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordWithSpaceInEnd);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(4).getText();

        String res = StringUtils.join(text);
        Assert.assertEquals(res, errorPasswordMessageAboutSecondPassword);
    }

    @Test //40
    public void writeUsedEmail(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(login);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        WebElement pressCreateAccountButton = driver.findElement(By.xpath("//div[@class='auth__action']"));
        pressCreateAccountButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-body custom-toast-width']")));
    }

    @Test //41
    public void writeLongEmail(){
        WebElement moveToRegistration = driver.findElement(By.xpath("//a[@class='auth__text auth__text_link']"));
        moveToRegistration.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_title']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__container']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='auth__text auth__text_simple']")));

        List<WebElement> inputFirstName = driver.findElements(By.xpath("//input[@step='any']"));
        inputFirstName.get(0).sendKeys(firstName);

        List<WebElement> inputLastName = driver.findElements(By.xpath("//input[@step='any']"));
        inputLastName.get(1).sendKeys(lastName);

        List<WebElement> inputEmail = driver.findElements(By.xpath("//input[@step='any']"));
        inputEmail.get(2).sendKeys(bigEmail);

        List<WebElement> inputPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputPassword.get(3).sendKeys(passwordForRegistration);

        List<WebElement> inputSecondPassword = driver.findElements(By.xpath("//input[@step='any']"));
        inputSecondPassword.get(4).sendKeys(passwordForRegistration);

        List<WebElement> checkErrorMessage = driver.findElements(By.xpath("//div[@class='ctm-field__err']"));
        String text = checkErrorMessage.get(2).getText();
        if (text.isEmpty()){
            System.out.println("OK");
        }else{
            throw new IllegalArgumentException("ERROR");
        }
    }
}
