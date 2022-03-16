package test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.*;

import java.text.ParseException;
import java.util.Locale;

public class Runner extends Hooks {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate successful login")
    @Story("Profile Stories")
    @Test
    public void validUserLogin() {
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        LandingPage landingPage = new LandingPage(getDriver());
        LoginPage loginPage = landingPage.clickLoginBtn();
        loginPage.sendKeysUsernameInput("danielfell400")
                        .sendKeysPasswordInput("hola123");
        ProfilePage profilePage = loginPage.clickInLoginBtn();
        Assert.assertEquals(profilePage.getUsernameTittle().toLowerCase(Locale.ROOT),"danielfell400");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate failed login")
    @Story("Profile Stories")
    @Test
    public void invalidUserLogin(){
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.clickLoginBtn();
        loginPage.sendKeysUsernameInput("axaxaxaxax")
                .sendKeysPasswordInput("axaxaxax").clickInLoginBtn();
        Assert.assertEquals(loginPage.getErrorStatusText()," There was a problem");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Search Movie From landing Page and validate that the first result is valid")
    @Story("Actions with movies")
    @Test
    public void searchMovieFromlandingPage() {
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.sendKeysMoviesInput("fight club");
        SearchPage searchPage = landingPage.clickInSearchBtn();
        Assert.assertEquals(searchPage.getTheFirstResult().getText().toLowerCase(Locale.ROOT),"fight club");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the user should see the genre of the movie includes 'action'")
    @Story("Actions with movies")
    @Test
    public void verifyMovieGenreFilter(){
        System.out.println("Ajiaco t1");
        System.out.println(driver.hashCode());
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        LandingPage landingPage = new LandingPage(getDriver());
        MoviesPage moviesPage = landingPage.clickInTopRatedMovies();
        moviesPage.clickInFilter().putActionFilter().clickInFilter().applyFilters();
        MovieDetailsPage movieDetailsPage = moviesPage.selectRandomMovie();
        Assert.assertEquals(movieDetailsPage.containsFilter("Action"),Boolean.TRUE);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the title of the movie should be in the timeline")
    @Story("Actions with movies details")
    @Test
    public void validateActingTimeline() {
        System.out.println("Ajiaco t2");
        System.out.println(driver.hashCode());
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        LandingPage landingPage = new LandingPage(getDriver());
        landingPage.sendKeysMoviesInput("fight club");
        SearchPage searchPage = landingPage.clickInSearchBtn();
        MovieDetailsPage movieDetailsPage = searchPage.clickInTheFirstResult();
        ActorPage actorPage = movieDetailsPage.selectAnActor();

        Assert.assertEquals(actorPage.verifyIfTheMoviesIsContained("fight club"),Boolean.FALSE);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the ascending date sort")
    @Story("Actions with movies")
    @Test
    public void sortByDatesOnAscendingOrder() throws ParseException {
        System.out.println("Ajiaco t3");
        System.out.println(driver.hashCode());
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        LandingPage landingPage = new LandingPage(getDriver());
        MoviesPage moviesPage = landingPage.clickInTopRatedMovies();
        moviesPage.clickInSortDropDown().applySortting("Release Date Ascending");
        Assert.assertEquals(moviesPage.verifyReleaseDateAscending(),Boolean.TRUE);
    }

}
