package helpers;

import model.AuthUser;
// import java.util.Random;

public class DataHelper {

    // public static String generateRandomEmail(){
    //     return String.format("%s@testemail.com" , generateRandomString(7));
    // }

    // public static String generateRandomTitle(){
    //     return String.format("%s" , generateRandomString(7));
    // }

    // public static String generateRandomContent(){
    //     return String.format("%s" , generateRandomString(100));
    // }

    // public static String generateRandomName(){
    //     return String.format("%s" , generateRandomString(7));
    // }

    // private static String generateRandomString(int targetStringLength){
    //     int leftLimit = 97; // letter 'a'
    //     int rightLimit = 122; // letter 'z'
    //     Random random = new Random();
    //     StringBuilder buffer = new StringBuilder(targetStringLength);
    //     for (int i = 0; i < targetStringLength; i++) {
    //         int randomLimitedInt = leftLimit + (int)
    //                 (random.nextFloat() * (rightLimit - leftLimit + 1));
    //         buffer.append((char) randomLimitedInt);
    //     }
    //     String generatedString = buffer.toString();

    //     return generatedString;
    // }

    // read this from extenal source
    public static AuthUser getAdminUser(){
        return new AuthUser("admin", "password123");
    }
}