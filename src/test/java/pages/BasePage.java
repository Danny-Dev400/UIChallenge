package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForDisplayAnElement(By element, int time){
        WebDriverWait wait = new WebDriverWait(driver,3);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement mapLocator(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> mapListLocator(By locator){
        return driver.findElements(locator);
    }

}
