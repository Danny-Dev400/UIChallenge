package test;

import apiMethods.ApiMethodsList;
import apiMethods.ApiMethodsMovies;
import apiMethods.HooksApi;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class RunnerApiCase extends Hooks{

    private HooksApi api;

    @BeforeClass
    public void setUpApi() throws IOException {
        api = new HooksApi();
        System.out.println(api.getUser().getUsername());
    }

    @Test
    public void apiUItest(){
        ApiMethodsList apiMethodsList = new ApiMethodsList(api.getUser());
        apiMethodsList.createList(api.getUser().getApiKey(),api.getUser().getSessionId());
        ApiMethodsMovies apiMethodsMovies = new ApiMethodsMovies(api.getUser());
        apiMethodsMovies.addMovie(api.getUser().getApiKey(),api.getUser().getSessionId(),"634649");

        LandingPage landingPage = new LandingPage(getDriver());
        LoginPage loginPage = landingPage.clickLoginBtn();
        loginPage.sendKeysUsernameInput("danielfell400")
                .sendKeysPasswordInput("hola123");
        ProfilePage profilePage = loginPage.clickInLoginBtn();
        UserListsPage userListsPage = profilePage.clickInListBtn();
        ListDetailsPage listDetailsPage = userListsPage.ClickInSpecificList();
        MovieDetailsPage movieDetailsPage = listDetailsPage.selectMovieOfTheList(0);
        Assert.assertEquals(movieDetailsPage.containsTittle("Spider-Man: No Way Home"),Boolean.TRUE);
    }

}
