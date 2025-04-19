package com.flight.service;

import com.flight.dto.FlightRequest;
import com.flight.exception.FlightException;

import java.util.List;

public interface FlightService {

    public String createFlight(FlightRequest req) throws FlightException;
    public FlightRequest getFlightDetailsByName(String name) throws FlightException;
    public List<FlightRequest> getAllFlights() throws FlightException;
    public String deleteFlight(String name) throws FlightException;
    public FlightRequest updateFlight(String name,FlightRequest req)throws FlightException;
    //TODO
    //Implement Query based methods
    //Implement pagination and sorting
}
