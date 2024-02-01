package com.example.cloudbees.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity
@Table(name = "bookingDetails")
public class BookingDetails {

    @Id
    @GeneratedValue
    @Column(name = "BookingId")
    private Long id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "EmailId")
    private String emailId;
    @Column(name = "From")
    private String from;
    @Column(name = "To")
    private String to;
    @Column(name = "Price")
    private float price;

    @CreationTimestamp
    private Date createdAt;
    @CreationTimestamp
    private Date updatedAt;

    public BookingDetails(String firstName, String lastName, String emailId, String from, String to, float price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public BookingDetails() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(@NonNull String emailId) {
        this.emailId = emailId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
