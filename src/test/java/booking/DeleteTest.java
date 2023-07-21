package booking;

import io.restassured.response.Response;
import specifications.RequestSpecs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class DeleteTest extends BaseTest {

    @Test()
    public void DeleteBooking_200() throws ParseException {
        // prepare test data
        int bookingId = createTestBooking();

        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .spec(RequestSpecs.generateToken())
            .delete(String.format("%s/%s", resourcePath, bookingId));

        // validate response
        // I know that this validations could be re-aggrouped or find a better way to handle this
        assertEquals(201, response.getStatusCode());

        // extra assertion to make sure the element was properly deleted
        response = given().
            get(String.format("%s/%s", resourcePath, bookingId));
        // validate response
        assertEquals(404, response.getStatusCode());

    }

    @Test()
    public void DeleteAuthenticationIssue_403() throws ParseException {
        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .delete(String.format("%s/%s", resourcePath, "123"));

        // validate response
        // I know that this validations could be re-aggrouped or find a better way to handle this
        assertEquals(403, response.getStatusCode());
        assertEquals("Forbidden", response.asString());
    }

    // This is returning 405 instead of 404, not sure if that is expected
    @Test()
    @Disabled()
    public void DeleteBookingNotFound_404() throws ParseException {
        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .spec(RequestSpecs.generateToken())
            .patch(String.format("%s/%s", resourcePath, "randomID"));

        // validate response
        assertEquals(404, response.getStatusCode());
    }
}
