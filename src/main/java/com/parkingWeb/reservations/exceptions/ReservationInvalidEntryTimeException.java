package com.parkingWeb.reservations.exceptions;

public class ReservationInvalidEntryTimeException extends RuntimeException {
    
    public ReservationInvalidEntryTimeException(String message) {
        super(message);
    }
}