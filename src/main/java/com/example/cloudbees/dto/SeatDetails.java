package com.example.cloudbees.dto;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "SeatDetails")
public class SeatDetails {

    @Column(name = "SeatNo")
    private Integer seatNo;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "emailId")
    @Id
    private String emailAddress;
    @Column(name = "Section")
    private Character section;

    public SeatDetails(Integer seatNo, String firstName, String lastName, String emailAddress, Character section) {
        this.seatNo = seatNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.section = section;
    }

    public SeatDetails() {

    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(@NonNull String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Character getSection() {
        return section;
    }

    public void setSection(Character section) {
        this.section = section;
    }
}
