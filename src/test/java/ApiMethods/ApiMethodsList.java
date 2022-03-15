package ApiMethods;

import BuilderUser.User;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiMethodsList extends BaseApi{

    public ApiMethodsList(User user) {
        super(user);
    }

    public ApiMethodsList createList(String apiKey, String session){
        JSONObject list = new JSONObject();
        list.put("name","My firt list");
        list.put("description","This is the description of my dos");
        list.put("language","en");

        int listId = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(list.toJSONString())
                .when().post("/list?api_key="+apiKey+"&session_id="+session)
                .then().statusCode(201).log().body()
                .and().extract().path("list_id");

        list.put("list_id", ""+listId);
        writeJsonData(list.toJSONString());

        return this;
    }
}
