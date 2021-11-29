package com.parkingWeb.reservations.controllers;


import com.parkingWeb.reservations.repositories.ReservationRepository;
import com.parkingWeb.reservations.models.Reservation;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

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
}
