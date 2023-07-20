package specifications;

import helpers.RequestHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    //It runs before a specific group
    //@BeforeGroups("Authentication")
    public static RequestSpecification generateToken(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        String token = RequestHelper.getAdminToken();
        requestSpecBuilder.addHeader("Authorization","Basic " + token);
        return requestSpecBuilder.build();
    }

    public static RequestSpecification generateFakeToken(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Authorization","Basic Non valid token");
        return requestSpecBuilder.build();
    }
}