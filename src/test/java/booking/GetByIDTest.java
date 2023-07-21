package booking;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import helpers.DataHelper;

import java.text.ParseException;


public class GetByIDTest extends BaseTest {

    @Test()

    public void GetBookingByID_200() throws ParseException {
        // prepare test data
        String firstName  = DataHelper.generateRandomName();
        int bookingId = createTestBooking(firstName);

        // execute test
        // filter baed on test-data
        Response response = 
            given().
            get(String.format("%s/%s", resourcePath, bookingId));

        // validate response
        assertEquals(200, response.getStatusCode());
        assertEquals(firstName, response.jsonPath().getString("firstname"));
    }

    @Test()
    public void GetBookingByINotFound_404() throws ParseException {
        // prepare test data
        String fakeId = "0";
        
        // execute test
        Response response = given().
            get(String.format("%s/%s", resourcePath, fakeId));

        // validate response
        assertEquals(404, response.getStatusCode());
        assertEquals("Not Found", response.asString());
    }
}
