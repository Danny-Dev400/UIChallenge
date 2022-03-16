package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final Logger logger = LogManager.getLogger(LoginPage.class);

    private final By inputUsername = By.id("username");
    private final By inputPassword = By.id("password");
    private final By loginButton = By.id("login_button");
    private final By errorStatusModal = By.xpath("//div[@class='error_status card']//h2");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @Step("Send Keys Username Input")
    public LoginPage sendKeysUsernameInput(String userr){
        logger.debug("Typing the username: " + userr + " in the input username");
        mapLocator(inputUsername).sendKeys(userr);
        return new LoginPage(driver);
    }

    @Step("Send Keys Password Input")
    public LoginPage sendKeysPasswordInput(String pass){
        logger.debug("Typing the password: " + pass + " in the input password");
        mapLocator(inputPassword).sendKeys(pass);
        return new LoginPage(driver);
    }

    @Step("Click in login button")
    public ProfilePage clickInLoginBtn(){
        logger.debug("Click in the login button");
        mapLocator(loginButton).click();
        return new ProfilePage(driver);
    }

    @Step("Verifying Error Status Text")
    public String getErrorStatusText(){
        logger.info("Getting the error status modal and verifying");
        return waitForDisplayAnElement(errorStatusModal,3).getText();
    }

}
