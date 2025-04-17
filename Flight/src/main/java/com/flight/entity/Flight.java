package com.flight.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flight")

public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private String name;
    private String source;
    private String destination;

    public Flight() {
    }

    public Flight(int bookingId, String name, String source, String destination) {
        this.bookingId = bookingId;
        this.name = name;
        this.source = source;
        this.destination = destination;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
