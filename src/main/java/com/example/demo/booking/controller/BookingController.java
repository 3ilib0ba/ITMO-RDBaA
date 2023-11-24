package com.example.demo.booking.controller;

import com.example.demo.booking.dto.BookingDTO;
import com.example.demo.booking.model.Booking;
import com.example.demo.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") Long id) {
        log.info("get /bookings/{" + id + "}");
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> addNewBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        log.info("post /bookings");
        return ResponseEntity.ok(bookingService.addBooking(bookingDTO));
    }
}
