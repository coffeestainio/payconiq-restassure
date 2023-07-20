

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import helpers.DataHelper;

import java.text.ParseException;
import java.util.List;

import javax.xml.crypto.Data;

public class GetAllBookingsTest extends BaseTest {

    @Test()
    public void GetAllBookings_202() {
        // execute test
        Response response = given()
                .when()
                .get("/booking");

        // validate response
        Assert.assertEquals(200, response.getStatusCode());
        List<Integer> list = response.jsonPath().getList("bookingid");
        Assert.assertTrue("Contains elements", list.size() > 0);
        Assert.assertTrue("All bookings are greater than 0" , list.stream().allMatch(id -> id > 0));
    }

    @Test()
    public void GetBookingsWithFilter_200() throws ParseException {
        // prepare test data
        String firstName = DataHelper.generateRandomName();
        int bookingId = createTestBooking(firstName);

        // execute test
        Response response = given()
                .when()
                .get(String.format("%s?firstname=%s", bookingPath, firstName));

        // validate response
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(bookingId, response.jsonPath().getInt("[0].bookingid"));
    }

    // This tests the good practice of returning an empty list + 200 when no
    // elements match the query parameters
    @Test()
    public void GetBookingsWithFilterNotFound_200() throws ParseException {
        // execute test
        // filter based on randomdata
        Response response = given()
                .when()
                .get(String.format("%s?firstname=%s", bookingPath, DataHelper.generateRandomName()));

        // validate response
        List<Integer> list = response.jsonPath().getList("bookingid");
        Assert.assertTrue("Contains elements", list.size() == 0);
    }
}
