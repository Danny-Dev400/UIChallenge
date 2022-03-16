package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class ActorPage extends BasePage {

    private final Logger logger = LogManager.getLogger(ActorPage.class);

    By acting = By.className("tooltip");

    public ActorPage(WebDriver driver){
        super(driver);
    }

    @Step("Verify If TheMovies Is Contained")
    public boolean verifyIfTheMoviesIsContained(String Movie){
        logger.debug("Verify each web element until find the correct movie ....");
        boolean isContained = false;
        for(WebElement element : mapListLocator(acting)){
            if (element.getText().toLowerCase(Locale.ROOT).equals(Movie)){
                isContained = true;
            }
        }
        if (isContained) {
            logger.info("successful verify");
        } else {
            logger.warn("The movie is not isContained");
        }
        return isContained;
    }
}
