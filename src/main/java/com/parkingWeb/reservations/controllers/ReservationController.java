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

    @PostMapping("/reservation")
    Reservation newReservation(@RequestBody Reservation reservation) { 
            return reservationRepository.save(reservation);
    }

    @GetMapping("/reservations")
    List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }

    @GetMapping("/reservation/{id}")
    Reservation getReservation(@PathVariable String id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @PutMapping("/reservation/update/{id}")
    Reservation updateReservation(@RequestBody Reservation reservationUpdate, 
                                  @PathVariable String id) throws Exception {

        Reservation reservation = this.reservationRepository.findById(id).orElse(null);
        
        if (reservation == null) {
            throw new Exception("The code of reservation don't exist in ParkingWeb register");
        }

        reservation.setClientId(reservationUpdate.getClientId());
        reservation.setParkingLot(reservationUpdate.getparkingLot());
        reservation.setvehicleType(reservationUpdate.getvehicleType());
        reservation.setArrivalDate(reservationUpdate.getarrivalDate());
        reservation.setEstimatedTime(reservationUpdate.getEstimatedTime());
        reservation.setVehiclePlate(reservationUpdate.getVehiclePlate());

        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/reservation/delete/{id}")
    String deleteReservation(@PathVariable String id) throws Exception {
        
        Reservation reservation = this.reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            throw new Exception("The code of reservation don't exist in ParkingWeb register");
        }

        reservationRepository.deleteById(id);

        return "The reservation has been deleted";
    }
}
