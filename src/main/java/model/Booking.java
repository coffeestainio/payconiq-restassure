package model;

import java.util.Date;

public class Booking {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    // Constructor
    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid,
                   Date checkin, Date checkOut, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = new BookingDates(checkin, checkOut);
        this.additionalneeds = additionalneeds;
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

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    // Nested class for BookingDates
    public static class BookingDates {
        private Date checkin;
        private Date checkout;

        public BookingDates(Date checkin, Date checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }

        public Date getCheckin() {
            return checkin;
        }

        public void setCheckin(Date checkin) {
            this.checkin = checkin;
        }

        public Date getCheckout() {
            return checkout;
        }

        public void setCheckout(Date checkout) {
            this.checkout = checkout;
        }
    }
}
