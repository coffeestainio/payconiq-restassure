package booking;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;
import model.Booking;
import specifications.RequestSpecs;

import static io.restassured.RestAssured.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeAll;

import helpers.DataHelper;

@ExtendWith(AllureJunit5.class)
public class BaseTest {
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected static String resourcePath = Constants.RESOURCE_PATH;
    protected static RequestSpecification booker = RequestSpecs.booker();

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = Constants.BASE_URL;
    }

    /**
     * Creates a test booking for a hotel reservation using the provided first name and check-in date.
     * This method simulates the booking creation process for testing purposes. In a real-life framework,
     * the implementation may vary depending on the testing environment and requirements.
     * 
     * @param firstName The first name of the guest making the booking.
     * @param checkin The check-in date for the hotel reservation in the format "yyyy-MM-dd".
     * @return The booking ID or a unique identifier associated with the newly created booking.
     * @throws ParseException If there is an error parsing the check-in date string into a Date object.
     */
    public int createTestBooking(String firstName, String checkin, String checkout) throws ParseException {
        Booking testBooking = new Booking(
            firstName, 
            "some-last-name", 
            200, 
            true, 
            dateFormat.parse(checkin), 
            dateFormat.parse(checkout),
            "nothing");

        Response response = given()
            .spec(RequestSpecs.booker())
            .body(testBooking)
            .post(resourcePath);
       
        return response.jsonPath().get("bookingid");
    }

    // overloaded method to simplify data creation
    public int createTestBooking(String firstName) throws ParseException {
        return createTestBooking(firstName, "2023-01-01", "2023-01-05");
    }

    public int createTestBooking() throws ParseException {
        return createTestBooking(DataHelper.generateRandomName(), "2023-01-01", "2023-01-05");
    }
}