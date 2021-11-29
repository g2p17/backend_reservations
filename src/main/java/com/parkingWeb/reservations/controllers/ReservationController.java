package com.parkingWeb.reservations.controllers;


import com.parkingWeb.reservations.models.Reservation;
import com.parkingWeb.reservations.repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/reservation/{parkingLot}/count")
    Map<String, Integer> getCountReservation(@PathVariable String parkingLot) {
        Map<String, Integer> response = new HashMap<String, Integer>();
        List<Reservation> reservations = reservationRepository.findByParkingLot(parkingLot);
        Iterator<Reservation> iterReservation = reservations.iterator();
        Date currentDate = new Date();
        int countActiveReservation = 0;

        while(iterReservation.hasNext()) {

            Reservation reservation = iterReservation.next();
            if (currentDate.compareTo(reservation.getEntryTime()) >= 0 
                && currentDate.compareTo(reservation.getExitTime()) <= 0);
                countActiveReservation += 1;
        }

        response.put(String.format("Active reservation in %s", parkingLot), countActiveReservation);

        return response;        
    }

    @GetMapping("/reservation/{parkingLot}/parkingLot/customers")
    List<Reservation> getCustomers(@PathVariable String parkingLot) {
        Map<String, Reservation> vehiclePlateCustomer = new HashMap<String, Reservation>();
        List<Reservation> reservations = reservationRepository.findByParkingLot(parkingLot);
        Iterator<Reservation> iterReservation = reservations.iterator();

        while(iterReservation.hasNext()) {
            Reservation reservation = iterReservation.next();
            vehiclePlateCustomer.put(reservation.getVehiclePlate(), reservation);
        }

        return vehiclePlateCustomer.values().stream().collect(Collectors.toList());
    }
}
