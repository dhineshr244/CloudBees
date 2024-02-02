package com.example.cloudbees.service;

import com.example.cloudbees.dto.BookingDetails;
import com.example.cloudbees.dto.SeatDetails;
import com.example.cloudbees.exception.BookingIdNotFoundException;

import java.util.List;

public interface BookingService {

    BookingDetails bookTicket(BookingDetails bookingDetails);

    BookingDetails getDetailsById(Long bookingId) throws BookingIdNotFoundException;

    List<SeatDetails> getSeatDetailsBySection(Character section);

    void deleteUserByMailId(String emailId);

    SeatDetails updateSeatNumber(Integer seatNumber);
}
