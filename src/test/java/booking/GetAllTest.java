package booking;

import io.restassured.response.Response;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import helpers.DataHelper;

public class GetAllTest extends BaseTest {

    @Test()
    public void GetAllBookings_202() {
        // execute test
        Response response = given().
                get(resourcePath);

        // validate response
        assertEquals(200, response.getStatusCode());
        List<Integer> list = response.jsonPath().getList("bookingid");
        assertTrue(list.size() > 0, "Make sure we have multiple responses");
        assertTrue(list.stream().allMatch(id -> id > 0), "All bookings are greater than 0");
    }

    @Test()
    public void GetBookingsWithFilter_200() throws ParseException {
        // prepare test data
        String firstName = DataHelper.generateRandomName();
        int bookingId = createTestBooking(firstName);

        // execute test
        Response response = given().
                get(String.format("%s?firstname=%s", resourcePath, firstName));

        // validate response
        assertEquals(200, response.getStatusCode());
        assertEquals(bookingId, response.jsonPath().getInt("[0].bookingid"));
    }

    // I tried to test the boundaries, but the api had a very odd behavior sometimes with filtering
    // and therefore didn't want to explore it more.
    // I just want to illustrate the boundary testing is important
    @Test()
    @Disabled()
    @DisplayName("Test boundaries for checkin/checkout filter")
    public void GetTestCheckinDatesBoundariesFilter() throws ParseException {
        // prepare test data
        String checkin = "2058-05-03";
        String checkout = "2058-05-08";
        int bookingId = createTestBooking("not important", checkin, checkout);

        // within the range
        Response response = given().
                queryParam("checkin", "2058-05-01").
                queryParam("checkout", "2058-05-10").
                get(resourcePath);
        assertEquals(bookingId, response.jsonPath().getInt("[0].bookingid"));

        // test the day of the checkin
        response = given().
                queryParam("checkin", checkin).
                queryParam("checkout", checkout).
                get(resourcePath);
        assertEquals(bookingId, response.jsonPath().getInt("[0].bookingid"));

        // outside the boundaries
        response = given().
                queryParam("checkin", "2058-05-05").
                queryParam("checkout", "2058-05-06").
                get(resourcePath);
        assertNull(response.jsonPath().get("[0].bookingid"));

    }

    @Test()
    public void GetBookingsWitMultipleFilters_200() throws ParseException {
        // prepare test data
        String firstName = DataHelper.generateRandomName();
        String checkin = "2020-01-03";
        int bookingId = createTestBooking(firstName, checkin, "2020-01-10");

        // execute test
        Response response = given().
                queryParam("firstname", firstName).
                queryParam("checkin", checkin).
                get(resourcePath);

        // validate response
        assertEquals(200, response.getStatusCode());
        assertEquals(bookingId, response.jsonPath().getInt("[0].bookingid"));
    }

    @Test()
    public void GetBookings_200() throws ParseException {
        // prepare test data
        String firstName = DataHelper.generateRandomName();
        int bookingId = createTestBooking(firstName);

        // execute test
        Response response = given().
                queryParam("firstname", firstName).
                get(resourcePath);

        // validate response
        assertEquals(200, response.getStatusCode());
        assertEquals(bookingId, response.jsonPath().getInt("[0].bookingid"));
    }

    // This tests the good practice of returning an empty list + 200 when no
    // elements match the query parameters
    @Test()
    public void GetBookingsWithFilterNotFound_200() throws ParseException {
        // execute test
        // filter based on randomdata
        Response response = given()
                .get(String.format("%s?firstname=%s", resourcePath, DataHelper.generateRandomName()));

        // validate response
        List<Integer> list = response.jsonPath().getList("bookingid");
        assertEquals(list.size(), 0);
    }
}
