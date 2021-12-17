package com.parkingWeb.reservations.controllers;

import com.parkingWeb.reservations.exceptions.ReservationNotFoundException;
import com.parkingWeb.reservations.models.Reservation;
import com.parkingWeb.reservations.repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.parkingWeb.reservations.config.ResponseTransfer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Reservations.")
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @ApiOperation("Creates a new reservation.")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reservation")
    ResponseTransfer createReservation(@ApiParam("Reservation information for a new reservation to be created.") 
                                                @RequestBody Reservation reservation) {
        HashMap<String, Object> response = new HashMap<>();

        response.put("status", "Successful reservation");
        reservationRepository.save(reservation);

        return new ResponseTransfer("Successful reservation");
    }

    @ApiOperation("Returns the number of reservations by parking lot.")
    @ResponseBody
    @GetMapping("/reservation/{parkingLot}/count")
    ResponseTransfer getCountReservation(@ApiParam("Parking lot name to filter the reservations.")
                                                @PathVariable String parkingLot) {
        Map<String, Integer> response = new HashMap<String, Integer>();
        List<Reservation> reservations = reservationRepository.findByParkingLot(parkingLot);
        Iterator<Reservation> iterReservation = reservations.iterator();
        Date currentDate = new Date();
        int countActiveReservation = 0;

        while (iterReservation.hasNext()) {

            Reservation reservation = iterReservation.next();

            if ((reservation.getEntryTime()).after(currentDate) && (reservation.getExitTime()).after(currentDate)) 
                countActiveReservation += 1;
        }

        response.put("reservations", countActiveReservation);

        return new ResponseTransfer(String.valueOf(countActiveReservation));
    }

    @ApiOperation("Returns list of all Reservations but you cant get customers info.")
    @GetMapping("/reservation/{parkingLot}/parkingLot/customers")
    List<Reservation> getCustomers(@ApiParam("Parking lot name to filter the reservations.")
                                    @PathVariable String parkingLot) {
        Map<String, Reservation> vehiclePlateCustomer = new HashMap<String, Reservation>();
        List<Reservation> reservations = reservationRepository.findByParkingLot(parkingLot);
        Iterator<Reservation> iterReservation = reservations.iterator();

        while (iterReservation.hasNext()) {
            Reservation reservation = iterReservation.next();
            vehiclePlateCustomer.put(reservation.getVehiclePlate(), reservation);
        }

        return vehiclePlateCustomer.values().stream().collect(Collectors.toList());
    }

    @ApiOperation("Returns a specific reservation by their identifier. 404 if does not exist.")
    @GetMapping("/reservation/{id}")
    Reservation getReservation(@ApiParam("Id of the reservation to be obtained. Cannot be empty.") @PathVariable String id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));
    }

    @ApiOperation("Updates a reservation.")
    @PutMapping("/reservation/update/{id}")
    Reservation updateReservation(@RequestBody Reservation reservationUpdate,
                                    @ApiParam("Reservation information to update a reservation.") @PathVariable String id) {
    

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

    @ApiOperation("Deletes a reservation from the system. 404 if the reservation's identifier is not found.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/reservation/delete/{id}")
    String deleteReservation(@ApiParam("Id of the reservation to be deleted. Cannot be empty.") @PathVariable String id) {
        reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("The code of reservation don't exist in ParkingWeb register"));

        reservationRepository.deleteById(id);

        return "The reservation has been deleted";
    }
    
    @ApiOperation("Returns list of all Reservations by parking lot.")
    @GetMapping("/reservation/{parkingLot}/parkingLot")
    List<Reservation> getParkingLot(@ApiParam("Parking lot name to filter the reservations.")
                                        @PathVariable String parkingLot){
        return reservationRepository.findByParkingLot(parkingLot);
    }

    @ApiOperation("Returns list of all Reservations in the system.")
    @GetMapping("/reservations")
    List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }
}
