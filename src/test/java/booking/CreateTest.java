package booking;

import io.restassured.response.Response;
import model.Booking;
import specifications.RequestSpecs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;

import helpers.DataHelper;

import java.text.ParseException;

public class CreateTest extends BaseTest {

    @Test()
    public void CreateBooking_200() throws ParseException {
        // prepare test data
         Booking testBooking = new Booking(
            DataHelper.generateRandomName(), 
            DataHelper.generateRandomName(), 
            200, 
            false, 
            dateFormat.parse("2020-01-01"), 
            dateFormat.parse("2020-01-02"), 
            "nothing");

        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .body(testBooking)
            .post(resourcePath);

        // validate response
        // I know that this validations could be re-aggrouped or find a better way to handle this
        assertEquals(200, response.getStatusCode());
        assertEquals(testBooking.getFirstname(), response.jsonPath().getString("booking.firstname"));
        assertEquals(testBooking.getLastname(), response.jsonPath().getString("booking.lastname"));
        assertEquals(testBooking.getAdditionalneeds(), response.jsonPath().getString("booking.additionalneeds"));
        assertEquals(dateFormat.format(testBooking.getBookingdates().getCheckin()), response.jsonPath().getString("booking.bookingdates.checkin"));
        assertEquals(dateFormat.format(testBooking.getBookingdates().getCheckout()), response.jsonPath().getString("booking.bookingdates.checkout"));
    }

    @Test()
    public void CreateBookingNoBody_500() throws ParseException {
        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .post(resourcePath);

        // validate response
        // I know that this validations could be re-aggrouped or find a better way to handle this
        assertEquals(500, response.getStatusCode());
    }

    @Test()
    public void CreateBookingWithWrongHeaders_418() throws ParseException {
        // prepare test data
         Booking testBooking = new Booking(
            DataHelper.generateRandomName(), 
            DataHelper.generateRandomName(), 
            200, 
            false, 
            dateFormat.parse("2020-01-01"), 
            dateFormat.parse("2020-01-02"), 
            "nothing");

        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .body(testBooking)
            .header("Accept", "randomtype")
            .post(resourcePath);

        // validate response
        assertEquals(418, response.getStatusCode());
        assertEquals("I'm a Teapot", response.asString());
    }

}
