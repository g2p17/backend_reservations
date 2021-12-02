package com.parkingWeb.reservations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ReservationInvalidVehicleTypeAdvice {
    
    @ResponseBody
    @ExceptionHandler(ReservationInvalidVehicleTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String VehicleAdvice(ReservationInvalidVehicleTypeException ex){
        return ex.getMessage();
    }
}