package com.example.demo.booking.exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long bookingId) {
        super("Booking with id[" + bookingId.toString() + "] not found");
    }
}
