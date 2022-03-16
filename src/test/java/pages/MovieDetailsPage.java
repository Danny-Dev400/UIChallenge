package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class MovieDetailsPage extends BasePage {

    private final Logger logger = LogManager.getLogger(MovieDetailsPage.class);

    private final By genres = By.xpath("//span[@class='genres']/a");
    private final By topBilledCast = By.xpath("//ol/li[@class='card']");
    private final By titleMovie = By.cssSelector("div.title > h2 > a");

    public MovieDetailsPage(WebDriver driver){
        super(driver);
    }

    @Step("Verifying if the filter is contained ....")
    public boolean containsFilter(String queryFilter){
        boolean isContained = false;
        for (WebElement genre: mapListLocator(genres)){
            if (genre.getText().equals(queryFilter)){
                isContained = true;
            }
        }
        if (isContained) {
            logger.info("successful verify");
        } else {
            logger.warn("The filter doesn't exist in the movie");
        }
        return isContained;
    }

    @Step("Verifying if the title is correct ....")
    public boolean containsTittle(String queryFilter){
        if (mapLocator(titleMovie).getText().equals(queryFilter)){
            logger.info("Correct verification of the movie tittle '" + queryFilter +"'");
            return true;
        }
        logger.error("The title of the movie does not correspond with " + queryFilter);
        return false;
    }

    @Step("Secelt an actor in the top rated list")
    public ActorPage selectAnActor(){
        Random randomActor = new Random();
        logger.info("selecting an actor of the top billed cast");
        mapListLocator(topBilledCast).get(randomActor.nextInt(mapListLocator(topBilledCast).size())).click();
        return new ActorPage(driver);
    }

}
