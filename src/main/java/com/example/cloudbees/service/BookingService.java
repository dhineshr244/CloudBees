package com.example.cloudbees.service;

import com.example.cloudbees.dto.BookingDetails;
import com.example.cloudbees.dto.SeatDetails;

import java.util.List;

public interface BookingService {

    BookingDetails bookTicket(BookingDetails bookingDetails);

    BookingDetails getDetailsById(Long bookingId);

    List<SeatDetails> getSeatDetailsBySection(Character section);

    void deleteUserByMailId(String emailId);

    SeatDetails updateSeatNumber(Integer seatNumber);
}
