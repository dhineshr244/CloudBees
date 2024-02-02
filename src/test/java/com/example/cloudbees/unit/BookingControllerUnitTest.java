package com.example.cloudbees.unit;

import com.example.cloudbees.controller.BookingController;
import com.example.cloudbees.dto.BookingDetails;
import com.example.cloudbees.exception.BookingIdNotFoundException;
import com.example.cloudbees.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingControllerUnitTest {

    @InjectMocks
    BookingController bookingController;

    @Mock
    BookingService bookingService;

    @Test
    public void testGetDetailsById() throws BookingIdNotFoundException {
        BookingDetails bookingDetails = new BookingDetails("Test", "test", "test", "test", "test", 1.2f);
        when(bookingService.getDetailsById(anyLong())).thenReturn(bookingDetails);
        assertEquals(bookingDetails, bookingController.getDetailsById(1L).getBody());
    }

    @Test
    public void testBookTicket() {
        BookingDetails bookingDetails = new BookingDetails("Test", "test", "test", "test", "test", 1.2f);
        when(bookingService.bookTicket(any())).thenReturn(bookingDetails);
        assertEquals(bookingDetails, bookingController.bookTicket(bookingDetails).getBody());
    }
}
