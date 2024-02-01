package com.example.cloudbees.controller;

import com.example.cloudbees.dto.SeatDetails;
import com.example.cloudbees.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/details")
public class SeatDetailsController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/{section}")
    public ResponseEntity<List<SeatDetails>>getDetailsById(@PathVariable(name = "section") Character section) {
        return ResponseEntity.ok(bookingService.getSeatDetailsBySection(section));
    }
}
