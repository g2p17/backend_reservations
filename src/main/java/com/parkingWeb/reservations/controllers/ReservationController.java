package com.parkingWeb.reservations.controllers;

import com.parkingWeb.reservations.exceptions.ReservationNotFoundException;
import com.parkingWeb.reservations.repositories.ReservationRepository;
import com.parkingWeb.reservations.models.Reservation;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
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

    @PutMapping("/reservation/update/{id}")
    Reservation updateReservation(@RequestBody Reservation reservationUpdate, 
                                 @PathVariable String id) throws Exception, ReservationNotFoundException {

        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));

        reservation.setClientId(reservationUpdate.getClientId());
        reservation.setParkingLot(reservationUpdate.getparkingLot());
        reservation.setvehicleType(reservationUpdate.getvehicleType());
        reservation.setArrivalDate(reservationUpdate.getarrivalDate());
        reservation.setEstimatedTime(reservationUpdate.getEstimatedTime());
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
