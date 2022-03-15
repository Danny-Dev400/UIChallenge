package test;

import drivers.DriversManager;
import org.openqa.selenium.WebDriver;

public class ParallelHooks {



    public WebDriver getWebDriver(){
        DriversManager driverManagerFactory = new DriversManager();
        WebDriver driver = driverManagerFactory.loadDriver("CHROME");
        driver.manage().window().maximize();
        driver.navigate().to("https://www.themoviedb.org/");
        System.out.println("Ajiaco setup");
        System.out.println(driver.hashCode());
        return driver;
    }
}
