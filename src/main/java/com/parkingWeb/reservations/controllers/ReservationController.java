package com.parkingWeb.reservations.controllers;

import com.parkingWeb.reservations.repositories.ReservationRepository;
import com.parkingWeb.reservations.models.Reservation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations/")
    List<Reservation> getReservations(){
        return reservationRepository.findAll();        
    }
}
