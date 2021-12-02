package com.parkingWeb.reservations.config;

public class ResponseTransfer {

    private String reservation;

    public ResponseTransfer(String reservation) {
        this.reservation = reservation;
    }

    public String getreservation() {
        return reservation;
    }

    public void setreservation(String reservation) {
        this.reservation = reservation;
    }
}
