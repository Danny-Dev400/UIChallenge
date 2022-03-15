package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    private final Logger logger = LogManager.getLogger(ProfilePage.class);

    private final By usernameNametag = By.xpath("//div[@class='content_wrapper flex']//h2");
    private final By profileNavbarItems = By.cssSelector("#new_shortcut_bar > li");

    public ProfilePage(WebDriver driver){
        super(driver);
    }

    public String getUsernameTittle(){
        return mapLocator(usernameNametag).getText();
    }

    @Step("Click In List Button")
    public UserListsPage clickInListBtn(){
        logger.info("Go to list Pages");
        mapListLocator(profileNavbarItems).get(2).click();
        return new UserListsPage(driver);
    }



}
