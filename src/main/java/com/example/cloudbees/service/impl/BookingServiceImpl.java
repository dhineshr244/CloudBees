package com.example.cloudbees.service.impl;

import com.example.cloudbees.dto.BookingDetails;
import com.example.cloudbees.dto.SeatDetails;
import com.example.cloudbees.repo.BookingDetailsRepository;
import com.example.cloudbees.repo.SeatDetailsRepository;
import com.example.cloudbees.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.MultivaluedHashMap;
import java.util.*;

@Transactional
@org.springframework.stereotype.Service
public class BookingServiceImpl implements BookingService {

    MultivaluedHashMap<Character, Map<Integer, SeatDetails>> seatDetailsMap = new MultivaluedHashMap<>();

    @Autowired
    BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    SeatDetailsRepository seatDetailsRepository;

    @Override
    @Transactional
    public BookingDetails bookTicket(BookingDetails bookingDetails) {

        int seatNo = getRandomSeatNumber();

        if (getRandomSection() == 'A') {
            createSeatingDetailsInSection(seatDetailsMap, seatNo, 'A', bookingDetails);
        } else {
            createSeatingDetailsInSection(seatDetailsMap, seatNo, 'B', bookingDetails);
        }

        return bookingDetailsRepository.save(bookingDetails);
    }

    @Override
    public BookingDetails getDetailsById(Long bookingId) {
        Optional<BookingDetails> byId = bookingDetailsRepository.findById(bookingId);
        if (byId.isPresent()) {
            return byId.get();
        } else throw new RuntimeException("BookingId not found : " + bookingId);
    }

    private int getRandomSeatNumber() {
        Random r = new Random();
        int low = 1;
        int high = 50;
        return r.nextInt(high - low) + low;
    }

    private char getRandomSection() {
        Random r = new Random();
        return r.nextBoolean() ? 'A' : 'B';
    }

    private void createSeatingDetailsInSection(MultivaluedHashMap<Character, Map<Integer, SeatDetails>> seatDetailsMap,
                                               int seatNo,
                                               char section,
                                               BookingDetails bookingDetails) {

        boolean flag;
        do {
            if (!seatDetailsMap.containsKey('A') ||
                    !seatDetailsMap.containsKey('B') ||
                    seatDetailsMap.get(section).stream().noneMatch(i -> i.containsKey(seatNo))) {
                flag = createSeatDetails(seatDetailsMap, seatNo, section, bookingDetails);
            } else {
                flag = createSeatDetails(seatDetailsMap, getRandomSeatNumber(), section, bookingDetails);
            }
        } while (flag);
    }

    private boolean createSeatDetails(MultivaluedHashMap<Character, Map<Integer, SeatDetails>> seatDetailsMap,
                                      int seatNo,
                                      char section,
                                      BookingDetails bookingDetails) {

        Map<Integer, SeatDetails> sectionMap = new HashMap<>();
        SeatDetails seatDetails = new SeatDetails(seatNo,
                bookingDetails.getFirstName(),
                bookingDetails.getLastName(),
                bookingDetails.getEmailId(),
                section);
        sectionMap.put(seatNo, seatDetails);
        seatDetailsMap.add(section, sectionMap);
        seatDetailsRepository.save(seatDetails);
        return false;
    }

    @Override
    public List<SeatDetails> getSeatDetailsBySection(Character section) {

        List<SeatDetails> collect = null;
        for (Map<Integer, SeatDetails> i : seatDetailsMap.get(section)) {
            collect = new ArrayList<>(i.values());
        }
        return collect;
    }

    @Override
    @Transactional
    public void deleteUserByMailId(String emailId) {

        bookingDetailsRepository.deleteByEmailId(emailId);
        seatDetailsRepository.deleteByEmailAddress(emailId);
    }

    @Override
    @Transactional
    public SeatDetails updateSeatNumber(Integer seatNumber) {

        SeatDetails seatDetailsBySeatNo = seatDetailsRepository.getSeatDetailsBySeatNo(seatNumber);
        Map<Integer, SeatDetails> detailsMap =
                seatDetailsMap.get(seatDetailsBySeatNo.getSection()).stream().filter(i ->
                        i.containsKey(seatDetailsBySeatNo.getSeatNo())).findAny().orElse(null);
        if (!(detailsMap == null)) {
            detailsMap.remove(seatNumber);
            detailsMap.clear();

            Integer updatedSeatNumber = getRandomSeatNumber();
            seatDetailsRepository.updateSeatDetailsBySeatNo(updatedSeatNumber,
                    seatDetailsBySeatNo.getSeatNo());
            SeatDetails updatedSeatDetails = seatDetailsRepository.getSeatDetailsBySeatNo(updatedSeatNumber);
            detailsMap.put(updatedSeatNumber, updatedSeatDetails);
            seatDetailsMap.add(seatDetailsBySeatNo.getSection(), detailsMap);
            return updatedSeatDetails;
        } else { throw new RuntimeException("Seat not found: " + seatNumber);}
    }
}
