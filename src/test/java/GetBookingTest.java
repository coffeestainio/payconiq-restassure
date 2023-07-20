

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class GetBookingTest extends BaseTest {

    @Test()
    public void GetBookingByID_200() throws ParseException {
        // prepare test data
        String firstName  = "test-firstname";
        int bookingId = createTestBooking(firstName, "test-lastName");

        // filter baed on test-data
        Response response = given()
                .when()
                .get(bookingPath + "/" + bookingId);

        // Validate the response
        Assert.assertEquals(200, response.getStatusCode());

        // verify correct data is returned
        Assert.assertEquals(firstName, response.jsonPath().getString("firstname"));
    }

    @Test()
    public void GetBookingByINotFound_404() throws ParseException {
        // filter baed on test-data
        Response response = given()
                .when()
                .get(bookingPath + "/" + 0);

        // Validate the response
        Assert.assertEquals(404, response.getStatusCode());

        // verify correct data is returned
        Assert.assertEquals("Not Found", response.asString());
    }
}
