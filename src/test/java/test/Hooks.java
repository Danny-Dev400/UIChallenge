package test;

import drivers.DriversManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {

    private WebDriver driver;

    public Hooks(String driverType){
        setup(driverType);
    }

    public void setup(String driverType){
        DriversManager driverManagerFactory = new DriversManager();
        driver = driverManagerFactory.loadDriver(driverType);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.themoviedb.org/");
        System.out.println("Ajiaco setup");
        System.out.println(driver.hashCode());
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        driver.quit();
    }

}
