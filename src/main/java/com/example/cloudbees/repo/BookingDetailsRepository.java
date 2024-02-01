package com.example.cloudbees.repo;

import com.example.cloudbees.dto.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {

    void deleteByEmailId(String email);

    BookingDetails getBookingDetailsByEmailId(String emailId);
}
