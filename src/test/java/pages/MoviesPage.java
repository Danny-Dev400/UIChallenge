package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MoviesPage extends BasePage {

    private final Logger logger = LogManager.getLogger(LogManager.class);

    private final By filterDropDown = By.xpath("//h2[.='Filters']");
    private final By filter = By.xpath("//a[.='Action']");
    private final By applyFiltersBtnEnabled = By.xpath("//div[@class='apply small background_color light_blue enabled']//a[.='Search']");
    private final By applyFiltersBtnDisabled = By.xpath("//div[@class='apply small background_color light_blue disabled']//a[.='Search']");
    private final By sectionResultsMovies = By.xpath("//section[@id='media_results']/div/div/div[contains(@class,'card')]");
    private final By sortBtn = By.xpath("//div[@class='filter']/span");
    private By listOfSortOptions;

    public MoviesPage(WebDriver driver){
        super(driver);
    }

    @Step("Click In Filter")
    public MoviesPage clickInFilter(){
        logger.info("Click in dropdown filter");
        mapLocator(filterDropDown).click();
        return new MoviesPage(driver);
    }

    @Step("Put Action Filter")
    public MoviesPage putActionFilter(){
        logger.info("Click Action filter button");
        waitForDisplayAnElement(filter,5).click();
        return new MoviesPage(driver);
    }

    @Step("Applying Filters")
    public MoviesPage applyFilters(){
        logger.info("Click in the search button");
        mapLocator(applyFiltersBtnEnabled).click();
        return new MoviesPage(driver);
    }

    @Step("Click In Sort DropDown")
    public MoviesPage clickInSortDropDown(){
        logger.info("Click in sort dropdown button");
        mapLocator(sortBtn).click();
        return new MoviesPage(driver);
    }

    @Step("Apply Sortting")
    public MoviesPage applySortting(String sortType){
        listOfSortOptions = By.xpath(String.format("//ul[@id='sort_by_listbox']/li[.='%s']", sortType));
        logger.info("Select the sort type: " + sortType);
        logger.info("Waiting for sort list");
        waitForDisplayAnElement(listOfSortOptions,5).click();
        logger.info("Click in search button");
        mapLocator(applyFiltersBtnEnabled).click();
        logger.info("waiting for the filter to apply");
        waitForDisplayAnElement(applyFiltersBtnDisabled,5);
        return new MoviesPage(driver);
    }

    @Step("Verify Release Date Ascending")
    public boolean verifyReleaseDateAscending() throws ParseException {
        boolean isInAscendingOrder = true;
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        for (int i = 0; i < 4; i++) {
            logger.info("Checking the first 4 elements ...");
            String getDate1 = mapListLocator(sectionResultsMovies).get(i).getText().split("\n")[1];
            String getDate2 = mapListLocator(sectionResultsMovies).get(i+1).getText().split("\n")[1];
            Date date1 = sdf.parse(getDate1);
            Date date2 = sdf.parse(getDate2);
            if (date1.after(date2)){
                isInAscendingOrder = false;
            }
        }
        if (isInAscendingOrder){
            logger.info("The sort type is correct");
            return true;
        }else {
            logger.error("The sort type is incorrect");
            return false;
        }
    }

    @Step("Select Random Movie")
    public MovieDetailsPage selectRandomMovie(){
        Random randomMovie = new Random();
        logger.info("waiting for the search button to be disabled");
        waitForDisplayAnElement(applyFiltersBtnDisabled,5);
        logger.info("Select a random movie");
        mapListLocator(sectionResultsMovies)
                .get(randomMovie.nextInt(mapListLocator(sectionResultsMovies).size())).click();
        return new MovieDetailsPage(driver);
    }

}
