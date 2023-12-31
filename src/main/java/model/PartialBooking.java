package model;

// just an example of a partial update model
public class PartialBooking {

    private String firstname;
    private String lastname;

    // Constructor
    public PartialBooking(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Getters and Setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
