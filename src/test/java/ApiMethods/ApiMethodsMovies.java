package ApiMethods;

import BuilderUser.User;
import io.restassured.http.ContentType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class ApiMethodsMovies extends BaseApi {

    public ApiMethodsMovies(User user) {
        super(user);
    }

    public ApiMethodsMovies addMovie(String apiKey, String session ,String movieId){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("./src/test/java/Data/listId.json")) {
            JSONObject objList = (JSONObject) jsonParser.parse(reader);
            String listId = (String) objList.get("list_id");

            JSONObject movie = new JSONObject();
            movie.put("media_id",movieId);

            given().contentType(ContentType.JSON).accept(ContentType.JSON).body(movie.toJSONString())
                    .when().post(String.format("/list/%s/add_item",listId)+"?api_key="+apiKey+"&session_id="+session)
                    .then().statusCode(201).log().body();
            return this;

        }catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ApiMethodsMovies addMovies(String apiKey, String session){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("./src/test/java/Data/moviesId.json")) {
            JSONObject objMovies = (JSONObject) jsonParser.parse(reader);
            JSONArray jArr = (JSONArray) objMovies.get("movies");
            Iterator<String> iterator = jArr.iterator();

            FileReader reader2 = new FileReader("./src/test/java/Data/listId.json");
            JSONObject objList = (JSONObject) jsonParser.parse(reader2);
            String listId = (String) objList.get("list_id");

            while(iterator.hasNext()){
                JSONObject movie = new JSONObject();
                movie.put("media_id",iterator.next());

                given().contentType(ContentType.JSON).accept(ContentType.JSON).body(movie.toJSONString())
                        .when().post(String.format("/list/%s/add_item",listId)+"?api_key="+apiKey+"&session_id="+session)
                        .then().statusCode(201).log().body();

            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return this;
    }

}
