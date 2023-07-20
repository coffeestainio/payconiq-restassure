

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import helpers.DataHelper;

import java.text.ParseException;

public class GetBookingTest extends BaseTest {

    @Test()
    public void GetBookingByID_200() throws ParseException {
        // prepare test data
        String firstName  = DataHelper.generateRandomName();
        int bookingId = createTestBooking(firstName);

        // execute test
        // filter baed on test-data
        Response response = given()
                .when()
                .get(bookingPath + "/" + bookingId);

        // validate response
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(firstName, response.jsonPath().getString("firstname"));
    }

    @Test()
    public void GetBookingByINotFound_404() throws ParseException {
        // execute test
        Response response = given()
                .when()
                .get(String.format("%s/%s", bookingPath, 0);

        // validate response
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Not Found", response.asString());
    }
}
