package com.example.cloudbees.controller;

import com.example.cloudbees.dto.BookingDetails;
import com.example.cloudbees.dto.SeatDetails;
import com.example.cloudbees.exception.BookingIdNotFoundException;
import com.example.cloudbees.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/getDetailsById/{bookingId}")
    public ResponseEntity<BookingDetails> getDetailsById(@PathVariable(name = "bookingId") Long bookingId)
            throws BookingIdNotFoundException {
        return ResponseEntity.ok(bookingService.getDetailsById(bookingId));
    }

    @PostMapping
    public ResponseEntity<BookingDetails> bookTicket(@RequestBody BookingDetails bookingDetails) {
        return ResponseEntity.ok(bookingService.bookTicket(bookingDetails));
    }

    @DeleteMapping("/delete/{emailId}")
    public ResponseEntity deleteUser(@PathVariable(name = "emailId") String emailId) {

        bookingService.deleteUserByMailId(emailId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/seatNo/{seatNo}")
    public ResponseEntity<SeatDetails> updateSeatNumber(@PathVariable(name = "seatNo") Integer seatNo) {

        return ResponseEntity.ok(bookingService.updateSeatNumber(seatNo));
    }
}
