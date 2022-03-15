package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.BasePage;

import java.io.FileReader;
import java.io.IOException;

public class UserListsPage extends BasePage {

    private final Logger logger = LogManager.getLogger(UserListsPage.class);

    private final By listOfMoviesLists = By.cssSelector(".list h2");

    public UserListsPage(WebDriver driver){
        super(driver);
    }

    @Step("Click In Specific List Of Movies")
    public ListDetailsPage ClickInSpecificList(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("./src/test/java/Data/listId.json")) {
            JSONObject objMovies = (JSONObject) jsonParser.parse(reader);
            String nameList = (String) objMovies.get("name");
            logger.info("Verifying if the list is present .. ");
            for (WebElement element: mapListLocator(listOfMoviesLists)) {
                if (element.getText().equals(nameList)){
                    element.click();
                    return new ListDetailsPage(driver);
                }
            }
        } catch (IOException | ParseException e) {
            logger.error("Fail selecting a specific list");
            e.printStackTrace();
        }
        return new ListDetailsPage(driver);
    }
}
