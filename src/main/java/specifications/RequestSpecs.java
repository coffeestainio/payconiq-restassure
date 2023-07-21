package specifications;

import helpers.RequestHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    public static RequestSpecification generateToken(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        String token = RequestHelper.getAdminToken();
        requestSpecBuilder.addCookie("token",token);
        return requestSpecBuilder.build();
    }

    public static RequestSpecification generateFakeToken(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Authorization","Basic Non valid token");
        return requestSpecBuilder.build();
    }

    /**
     * Creates and returns a RequestSpecification for Booker API requests.
     * Containing the necessary default headers
     * 
     * @return A RequestSpecification instance with the necessary headers set for making requests to the Booker API.
     */
    public static RequestSpecification booker(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Accept", "application/json");
        requestSpecBuilder.addHeader("Content-Type" , "application/json");
        return requestSpecBuilder.build();
    }
}