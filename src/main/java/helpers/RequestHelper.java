package helpers;

import io.restassured.response.Response;
import specifications.RequestSpecs;

import static io.restassured.RestAssured.given;

public class RequestHelper {

    public static String getAdminToken(){
        Response response = 
            given().
            spec(RequestSpecs.booker()).
            body(DataHelper.getAdminUser()).
            post("/auth");

       
        return response.jsonPath().getString("token");
    }
}