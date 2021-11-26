package com.parkingWeb.reservations.exceptions;

public class ReservationNotFoundException extends RuntimeException{
        public ReservationNotFoundException(String message) {
            super(message);
        }
}