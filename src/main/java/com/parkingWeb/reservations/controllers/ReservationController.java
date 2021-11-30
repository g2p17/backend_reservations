package com.parkingWeb.reservations.controllers;

import com.parkingWeb.reservations.exceptions.ReservationNotFoundException;
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

        while (iterReservation.hasNext()) {

            Reservation reservation = iterReservation.next();
            if (currentDate.compareTo(reservation.getEntryTime()) >= 0
                    && currentDate.compareTo(reservation.getExitTime()) <= 0) ;
            countActiveReservation += 1;
        }

        response.put("reservations", countActiveReservation);

        return response;
    }

    @GetMapping("/reservation/{parkingLot}/parkingLot/customers")
    List<Reservation> getCustomers(@PathVariable String parkingLot) {
        Map<String, Reservation> vehiclePlateCustomer = new HashMap<String, Reservation>();
        List<Reservation> reservations = reservationRepository.findByParkingLot(parkingLot);
        Iterator<Reservation> iterReservation = reservations.iterator();

        while (iterReservation.hasNext()) {
            Reservation reservation = iterReservation.next();
            vehiclePlateCustomer.put(reservation.getVehiclePlate(), reservation);
        }

        return vehiclePlateCustomer.values().stream().collect(Collectors.toList());
    }


    @GetMapping("/reservation/{id}")
    Reservation getReservation(@PathVariable String id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));
    }

    @PutMapping("/reservation/update/{id}")
    Reservation updateReservation(@RequestBody Reservation reservationUpdate, @PathVariable String id) {    

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));

        reservation.setClientId(reservationUpdate.getClientId());
        reservation.setParkingLot(reservationUpdate.getparkingLot());
        reservation.setvehicleType(reservationUpdate.getvehicleType());
        reservation.setEntryTime(reservationUpdate.getEntryTime());
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
        @GetMapping("/reservation/{parkingLot}/parkingLot")
        List<Reservation> getParkingLot(@PathVariable String parkingLot){
            return reservationRepository.findByParkingLot(parkingLot);
        }

        @GetMapping("/reservations")
        List<Reservation> getReservations() {
            return reservationRepository.findAll();
        }
}
