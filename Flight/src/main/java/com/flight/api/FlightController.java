package com.flight.api;

import com.flight.dto.FlightRequest;
import com.flight.exception.FlightException;
import com.flight.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/flights")
@RestController
@Validated
public class FlightController {


    private FlightService flightService;
    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createFlight(@RequestBody FlightRequest req) throws FlightException{
        String flight = flightService.createFlight(req);
        return new ResponseEntity<>(flight,HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<FlightRequest> getFlightDetailsByName(@PathVariable String name) throws FlightException{
        FlightRequest details = flightService.getFlightDetailsByName(name);
        return new ResponseEntity<>(details,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<FlightRequest>> getAllFlights() throws FlightException{
        List<FlightRequest> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights,HttpStatus.OK);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteFlight(@PathVariable String name) throws FlightException{
       String s= flightService.deleteFlight(name);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @PutMapping("/update/{name}")
    public ResponseEntity<FlightRequest> updateFlight(@PathVariable String name,@RequestBody @Valid FlightRequest req)throws FlightException{
        FlightRequest flightRequest = flightService.updateFlight(name,req);
        return new ResponseEntity<>(flightRequest,HttpStatus.OK);
    }
}
