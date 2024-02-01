package com.example.cloudbees.repo;

import com.example.cloudbees.dto.SeatDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface SeatDetailsRepository extends JpaRepository<SeatDetails, Long> {

    void deleteByEmailAddress(String email);

    @Modifying(clearAutomatically = true)
    @Query("update SeatDetails seatDetails set seatDetails.seatNo =:updatedSeatNumber where seatDetails.seatNo =:seatNumber")
    void updateSeatDetailsBySeatNo(@Param("updatedSeatNumber") Integer updatedSeatNumber,
                                          @Param("seatNumber") Integer seatNumber);

    SeatDetails getSeatDetailsBySeatNo(Integer seatNumber);
}
