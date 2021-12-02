package com.parkingWeb.reservations.exceptions;

public class ReservationInvalidEstimatedTimeException extends RuntimeException {
    
    public ReservationInvalidEstimatedTimeException(String message) {
        super(message);
    }
}