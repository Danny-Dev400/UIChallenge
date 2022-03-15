package ApiMethods;

import BuilderUser.User;
import BuilderUser.UserBuilder;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import test.Hooks;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static io.restassured.RestAssured.*;

public class HooksApi {

    private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    static SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
    Properties props = new Properties();
    private UserBuilder userBuilder = new UserBuilder(props.getProperty("apiKey"));
    private User user = new User();

    public HooksApi() throws IOException {
        loadProperties();
    }

    public User getUser() {
        return user;
    }

    public void loadProperties() throws IOException {
        baseURI = "https://api.themoviedb.org/3";

        InputStream inputStream = new FileInputStream("./src/main/resources/credentials.properties");
        props.load(inputStream);
        userBuilder = new UserBuilder(props.getProperty("apiKey"));
        Date date = new Date();
        Date date1;
        try {
            date1 = sdf.parse(props.getProperty("TokenExpires"));
        }catch (Exception e){
            date1 = null;
        }
        if(date1 == null || date.after(date1) ){
            getToken();
            validateToken();
            createSessionn();
            storeProperties();
            user = userBuilder.setUsernameBuilder(props.getProperty("username"))
                    .setPasswordBuilder(props.getProperty("password"))
                    .setExpiresTokenBuilder(props.getProperty("TokenExpires"))
                    .setTokenBuilder(props.getProperty("token"))
                    .setSessionIdBuilder(props.getProperty("sessionId"))
                    .build();
        }else {
            System.out.println("Secion valida");
            user = userBuilder.setUsernameBuilder(props.getProperty("username"))
                    .setPasswordBuilder(props.getProperty("password"))
                    .setExpiresTokenBuilder(props.getProperty("TokenExpires"))
                    .setTokenBuilder(props.getProperty("token"))
                    .setSessionIdBuilder(props.getProperty("sessionId"))
                    .build();
        }
    }

    public void getToken() {
        String token = given().params("api_key",props.getProperty("apiKey"))
                .when().get("/authentication/token/new")
                .then().statusCode(200)
                .and().extract().path("request_token");

        props.setProperty("token",token);
    }

    public void validateToken(){
        JSONObject user = new JSONObject();
        user.put("username",props.getProperty("username"));
        user.put("password",props.getProperty("password"));
        user.put("request_token",props.getProperty("token"));

        String expire = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(user.toJSONString())
                .when().post("/authentication/token/validate_with_login?api_key="+props.getProperty("apiKey"))
                .then().statusCode(200)
                .and().extract().path("expires_at");

        props.setProperty("TokenExpires",expire.replaceAll(" UTC",""));
    }

    public void createSessionn(){
        JSONObject createSession = new JSONObject();
        createSession.put("request_token",props.getProperty("token"));

        String sessionId = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(createSession.toJSONString())
                .when().post("/authentication/session/new?api_key="+props.getProperty("apiKey"))
                .then().statusCode(200)
                .and().extract().path("session_id");

        props.setProperty("sessionId",sessionId);
    }

    public void storeProperties() throws IOException {
        OutputStream output = new FileOutputStream("./src/main/resources/credentials.properties");
        props.store(output,null);
    }

}
