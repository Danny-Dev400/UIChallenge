package utilities;

import java.util.Random;

public class Utilities {

    static String abc = "ABCDEFGHIJKMNLOPQRSTUVWXYZ";

    public static String generateRandomString(){
        String result = "";
        for(int i = 0; i < 5;i++){
            Random r = new Random();
            result += "" + abc.charAt(r.nextInt(26));
        }
        return result;
    }
}
