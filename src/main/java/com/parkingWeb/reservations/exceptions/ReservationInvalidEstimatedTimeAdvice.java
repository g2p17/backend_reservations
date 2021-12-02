package com.parkingWeb.reservations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ReservationInvalidEstimatedTimeAdvice {
    
    @ResponseBody
    @ExceptionHandler(ReservationInvalidEstimatedTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String VehicleAdvice(ReservationInvalidEstimatedTimeException ex){
        return ex.getMessage();
    }
}