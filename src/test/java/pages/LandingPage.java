package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LandingPage extends BasePage {

    private final Logger logger = LogManager.getLogger(LandingPage.class);

    private final By loginBtn = By.xpath("//ul[@class='primary']//li[3]/a");
    private final By movieSearchBar = By.id("inner_search_v4");
    private final By searchMovieBtn = By.xpath("//form[@id='inner_search_form']/input");
    private final By moviesBtn = By.xpath("//a[.='Movies']");
    private final By topRatedMovieBtn = By.xpath("//a[@href='/movie/top-rated']");

    public LandingPage(WebDriver driver){
        super(driver);
    }

    @Step("Click in login Button")
    public LoginPage clickLoginBtn(){
        logger.debug("Click in the login Button");
        mapLocator(loginBtn).click();
        Allure.addAttachment("My attachment", logger.getMessageFactory().toString());
        return new LoginPage(driver);
    }

    @Step("Send Keys Movies Input")
    public LoginPage sendKeysMoviesInput(String query){
        logger.debug("Typing the movie: " + query + " in the search input");
        mapLocator(movieSearchBar).sendKeys(query);
        return new LoginPage(driver);
    }

    @Step("Click in search btn")
    public SearchPage clickInSearchBtn(){
        logger.debug("Click in search btn");
        mapLocator(searchMovieBtn).click();
        return new SearchPage(driver);
    }

    @Step("Click In Top Rated Movies")
    public MoviesPage clickInTopRatedMovies() {
        logger.info("Load Movies page");
        Actions action = new Actions(driver);
        logger.debug("Hover in the action button ");
        action.moveToElement(mapLocator(moviesBtn)).perform();
        mapLocator(topRatedMovieBtn).click();
        logger.debug("Click in top rated movies");
        return new MoviesPage(driver);
    }


}
