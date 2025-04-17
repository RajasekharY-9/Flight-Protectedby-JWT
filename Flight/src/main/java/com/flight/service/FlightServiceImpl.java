package com.flight.service;

import com.flight.dto.FlightRequest;
import com.flight.entity.Flight;
import com.flight.exception.FlightException;
import com.flight.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{

    private FlightRepository flightRepository;
@Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @Override
    public String createFlight(FlightRequest req) throws FlightException {
        Flight book=new Flight();
        book.setName(req.getName());
        book.setDestination(req.getDestination());
        book.setSource(req.getSource());
        Flight savedFlightBooking = flightRepository.save(book);

        return "Flight Created ID : "+savedFlightBooking.getBookingId();
    }

    @Override
    public FlightRequest getFlightDetailsByName(String name) throws FlightException {
        Flight flight=   flightRepository.findByName(name);
        if(flight==null){
            throw new FlightException("SERVICE_FLIGHT_NOT_EXIST");
        }
        return convertEntityToDTO(flight);
    }

    @Override
    public List<FlightRequest> getAllFlights() throws FlightException {
        List<Flight> all = flightRepository.findAll();
       if(all.isEmpty()){
           return null;
       }
        List<FlightRequest> dtos =all.stream().map(x->convertEntityToDTO(x)).collect(Collectors.toList());

        return dtos;
    }
    public FlightRequest convertEntityToDTO(Flight f){
        FlightRequest dto=new FlightRequest();
        dto.setDestination(f.getDestination());
        dto.setSource(f.getSource());
        dto.setName(f.getName());
        return dto;
    }

    @Override
    public String deleteFlight(String name) throws FlightException {
        Flight flight=   flightRepository.findByName(name);
        if(flight==null){
            throw new FlightException("SERVICE_FLIGHT_NOT_EXIST");
        }
        flightRepository.deleteByName(name);
        return "Flight "+name+" has been removed";
    }

    @Override
    public FlightRequest updateFlight(FlightRequest req) throws FlightException {

        Flight f=new Flight();
        f.setSource(req.getSource());
        f.setDestination(req.getDestination());
        f.setName(req.getName());
        Flight save = flightRepository.save(f);
        return convertEntityToDTO(save);
    }
}
