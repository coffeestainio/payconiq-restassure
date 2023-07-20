

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class GetAllBookingsTest extends BaseTest {

    @Test()
    public void GetAllBookings_202() {
        // Perform the API request
        Response response = given()
                .when()
                .get("/booking");

        // Validate the response
        Assert.assertEquals(200, response.getStatusCode());

        // verify size
        List<Integer> list = response.jsonPath().getList("bookingid");
        Assert.assertTrue("Contains elements", list.size() > 0);
        Assert.assertTrue("All bookings are greater than 0" , list.stream().allMatch(id -> id > 0));
    }

    @Test()
    public void GetBookingsWithFilter_202() throws ParseException {
        // prepare test data
        int bookingId = createTestBooking("test-name", "test-lastName");

        // filter baed on test-data
        Response response = given()
                .when()
                .get(getBookingsPath + "?firstname=test-name&lastname=test-lastName");

        // Validate the response
        Assert.assertEquals(200, response.getStatusCode());

        // verify correct data is returned
        Assert.assertEquals(bookingId, response.jsonPath().getInt("$[0].bookingid"));
    }
}
