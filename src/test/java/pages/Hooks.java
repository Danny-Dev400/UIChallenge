package pages;

import drivers.DriversManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.ByteArrayInputStream;

public class Hooks {

    protected static ThreadLocal<WebDriver> driver =  new ThreadLocal<>();

    @BeforeMethod
    @Parameters({ "typedriver" })
    public void setup(String typedriver){
        DriversManager driversManager = new DriversManager();
        driver.set(driversManager.loadDriver(typedriver));
        getDriver().manage().window().maximize();
        getDriver().navigate().to("https://www.themoviedb.org/");
        System.out.println("Ajiaco setup");
        System.out.println(driver.hashCode());
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (!result.isSuccess()){
            byte[] myScreenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot",new ByteArrayInputStream(myScreenshot));
        }
        getDriver().quit();
    }

}
