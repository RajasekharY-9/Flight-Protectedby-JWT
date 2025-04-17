package com.flight.repository;

import com.flight.dto.FlightRequest;
import com.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Integer> {


    Flight findByName(String name);

    void deleteByName(String name);
}
