package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListDetailsPage extends BasePage {

    private final Logger logger = LogManager.getLogger(ListDetailsPage.class);

    private final By firstMovieOfTheList = By.cssSelector(".item");

    public ListDetailsPage(WebDriver driver){
        super(driver);
    }

    @Step("Select Movie Of The List")
    public MovieDetailsPage selectMovieOfTheList(int indexMovie){
        logger.info("Click in a movie with the index " + indexMovie + " in the list of results");
        mapListLocator(firstMovieOfTheList).get(indexMovie).click();
        return new MovieDetailsPage(driver);
    }



}
