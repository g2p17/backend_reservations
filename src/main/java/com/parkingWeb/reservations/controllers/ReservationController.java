package com.parkingWeb.reservations.controllers;


import com.parkingWeb.reservations.repositories.ReservationRepository;
import com.parkingWeb.reservations.models.Reservation;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Date;
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
}
