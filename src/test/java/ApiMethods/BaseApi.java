package ApiMethods;

import BuilderUser.User;

import java.io.FileWriter;
import java.io.IOException;

public class BaseApi {

    private User user;

    public BaseApi(User user){
        this.user = user;
    }

    public void writeJsonData(String jsonObj) {
        try {
            FileWriter fw = new FileWriter("./src/test/java/data/listId.json");
            fw.write(jsonObj);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error building Json");
        }
    }
}
