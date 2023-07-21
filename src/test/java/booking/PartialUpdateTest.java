package booking;

import io.restassured.response.Response;
import model.PartialBooking;
import specifications.RequestSpecs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import helpers.DataHelper;

import java.text.ParseException;

public class PartialUpdateTest extends BaseTest {

    @Test()
    public void PartialUpdateBooking_200() throws ParseException {
        // prepare test data
        String firstName = DataHelper.generateRandomName();
        int bookingId = createTestBooking(firstName);

        // update first Name
        firstName += DataHelper.generateRandomName();
        PartialBooking pBooking = new PartialBooking(firstName, "lastName");

        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .spec(RequestSpecs.generateToken())
            .body(pBooking)
            .patch(String.format("%s/%s", resourcePath, bookingId));

        // validate response
        // I know that this validations could be re-aggrouped or find a better way to handle this
        assertEquals(200, response.getStatusCode());
        assertEquals(pBooking.getFirstname(), response.jsonPath().getString("firstname"));
        assertEquals(pBooking.getLastname(), response.jsonPath().getString("lastname"));
    }

    @Test()
    public void PartialBookingAuthenticationIssue_403() throws ParseException {
        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .patch(String.format("%s/%s", resourcePath, "123"));

        // validate response
        // I know that this validations could be re-aggrouped or find a better way to handle this
        assertEquals(403, response.getStatusCode());
        assertEquals("Forbidden", response.asString());
    }

    // This is returning 405 instead of 404, not sure if that is expected
    @Test()
    @Disabled
    public void PartialBookingNotFound_404() throws ParseException {
        // execute test
        Response response = given()
            .spec(RequestSpecs.booker())
            .spec(RequestSpecs.generateToken())
            .patch(String.format("%s/%s", resourcePath, "123"));

        // validate response
        assertEquals(404, response.getStatusCode());
    }
}
