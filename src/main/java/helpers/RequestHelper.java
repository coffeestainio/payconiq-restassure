package helpers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestHelper {

    public static String getAdminToken(){
        Response response = given().body(DataHelper.getAdminUser()).post("/auth");

        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("token.access_token");
        return token;
    }
}