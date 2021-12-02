package com.parkingWeb.reservations.exceptions;

public class ReservationInvalidVehicleTypeException extends RuntimeException{
    public ReservationInvalidVehicleTypeException(String message) {
        super(message);
    }
}