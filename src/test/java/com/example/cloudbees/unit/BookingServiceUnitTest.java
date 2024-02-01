package com.example.cloudbees.unit;

import com.example.cloudbees.dto.BookingDetails;
import com.example.cloudbees.repo.BookingDetailsRepository;
import com.example.cloudbees.repo.SeatDetailsRepository;
import com.example.cloudbees.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingServiceUnitTest {

    @Mock
    BookingDetailsRepository bookingDetailsRepository;

    @Mock
    SeatDetailsRepository seatDetailsRepository;

    @InjectMocks
    BookingServiceImpl bookingService;

    @Test
    public void testBookTicket() {

        BookingDetails bookingDetails = new BookingDetails("Test", "test", "test", "test", "test", 1.2f);
        bookingService.bookTicket(bookingDetails);
    }

}
