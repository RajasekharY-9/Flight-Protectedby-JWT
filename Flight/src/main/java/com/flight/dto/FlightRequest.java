package com.flight.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


public class FlightRequest {

    @NotNull(message = "{flight.name.notnull}")
    private String name;
    @NotNull(message = "{flight.source.notnull}")
    private String source;
    @NotNull(message = "{flight.destination.notnull}")
    private String destination;

    public FlightRequest() {
    }

    public FlightRequest(String name, String source, String destination) {
        this.name = name;
        this.source = source;
        this.destination = destination;
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
