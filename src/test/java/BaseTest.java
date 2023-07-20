

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Booking;

import static io.restassured.RestAssured.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;


public class BaseTest {

    protected static String getBookingsPath = "/bookings";
    protected static String bookingPath = "/booking";

    private static String BASE_URL = "https://restful-booker.herokuapp.com";

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    public int createTestBooking(String firstName, String lastName) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Booking testBooking = new Booking(
            firstName, 
            lastName, 
            200, 
            true, 
            dateFormat.parse("2023-01-01"), 
            dateFormat.parse("2023-02-01"),
            "nothing");

        Response response = given()
            .headers("Accept", "application/json")
            .headers("Content-Type" , "application/json")
            .body(testBooking)
            .post(bookingPath);
       
        return response.jsonPath().get("bookingid");
    }
}