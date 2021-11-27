package com.parkingWeb.reservations.controllers;

import com.parkingWeb.reservations.exceptions.ReservationNotFoundException;
import com.parkingWeb.reservations.repositories.ReservationRepository;
import com.parkingWeb.reservations.models.Reservation;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;


@RestController
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reservation")
    HashMap<String, Object> createReservation(@RequestBody Reservation reservation) {
        HashMap<String, Object> response = new HashMap<>();
        
        response.put("status", "Successful reservation");        
        reservationRepository.save(reservation);

        return response;
    }

    @GetMapping("/reservations")
    List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }

    @GetMapping("/reservation/{id}")
    Reservation getReservation(@PathVariable String id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));
    }

    @GetMapping("/reservation/{parkingLot}/customers")
    List<Reservation> getCustomers(@PathVariable String parkingLot) {        
        Map<String, Reservation> hm = new HashMap<String, Reservation>();
        List<Reservation> reservations = reservationRepository.findByParkingLot(parkingLot);
        Iterator<Reservation> i = reservations.iterator();

        while(i.hasNext()) {
            Reservation reservation = i.next();
            hm.put(reservation.getVehiclePlate(), reservation);            
        }

        List<Reservation> valueList = hm.values().stream().collect(Collectors.toList());      

        return valueList;
    }

    @PutMapping("/reservation/update/{id}")
    Reservation updateReservation(@RequestBody Reservation reservationUpdate, 
                                 @PathVariable String id) throws Exception, ReservationNotFoundException {

        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));

        reservation.setClientId(reservationUpdate.getClientId());
        reservation.setParkingLot(reservationUpdate.getparkingLot());
        reservation.setvehicleType(reservationUpdate.getvehicleType());
        reservation.setEntryTime(reservationUpdate.getExitTime());
        reservation.setEstimatedTime(reservationUpdate.getEstimatedTime());
        reservation.setExitTime(reservationUpdate.getEstimatedTime());
        reservation.setVehiclePlate(reservationUpdate.getVehiclePlate());

        return reservationRepository.save(reservation);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/reservation/delete/{id}")
    String deleteReservation(@PathVariable String id) {
        reservationRepository.findById(id)
            .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));

        reservationRepository.deleteById(id);

        return "The reservation has been deleted";
    }
}
