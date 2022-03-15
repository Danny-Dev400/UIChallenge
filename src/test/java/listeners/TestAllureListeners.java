package listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.Hooks;

public class TestAllureListeners implements ITestListener {

    private WebDriver driver;

    public TestAllureListeners(){}

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot(driver);
        System.out.println("Fallo en esto: " + result.getMethod());
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        System.out.printf("laksndlksan;knd");
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Stacktrace", type = "text/plain")
    public static String saveLogs(String logs) {
        return logs;
    }

}
