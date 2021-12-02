package com.parkingWeb.reservations.models;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.parkingWeb.reservations.exceptions.ReservationInvalidEntryTimeException;
import com.parkingWeb.reservations.exceptions.ReservationInvalidEstimatedTimeException;
import com.parkingWeb.reservations.exceptions.ReservationInvalidVehicleTypeException;

@ApiModel(description = "Class representing a reservation tracked by the application.")
public class Reservation {

    @ApiModelProperty(notes = "Unique identifier of the reservation.", example = "61a56c4f15b2234fec4383c6", required = true, position = 0)
    @Id
    private String  id;
    @ApiModelProperty(notes = "reference to the identifier of the customer who is making this reservation.", example = "John", required = true, position = 1)
    private String    clientId;
    @ApiModelProperty(notes = "Parking lot name.", example = "California", required = true, position = 2)
    private String  parkingLot;
    @ApiModelProperty(notes = "References vehicle: vehicle type.", example = "Car, Motorcycle, Bicycle, Disabled Parking", required = true, position = 3)
    private String  vehicleType;
    @ApiModelProperty(notes = "stores the customer’s arrival date.", example = "2021-12-25T07:01:00.405Z", required = true, position = 4)
    private Date    entryTime;
    @ApiModelProperty(notes = "stores the customer’s departure (exit) date.", example = "2021-12-25T07:01:00.405Z", required = true, position = 5)
    private Date    exitTime;
    @ApiModelProperty(notes = "stores the duration for which the reservation was made in minutes.", example = "60", required = true, position = 6)
    private int     estimatedTime;
    @ApiModelProperty(notes = "References vehicle: vehicle plate.", example = "60DA3A", required = true, position = 7)
    private String  vehiclePlate;


    public Reservation(String id, String clientId, String parkingLot, String vehicleType, 
        Date entryTime, int estimatedTime, String vehiclePlate) {
        this.id = id;
        this.clientId = clientId;
        this.parkingLot = parkingLot;
        this.vehiclePlate = vehiclePlate;        
        this.setvehicleType(vehicleType);
        this.setEntryTime(entryTime);
        this.setEstimatedTime(estimatedTime);
        this.setExitTime(estimatedTime);
    };

    public String getId() {
        return id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setParkingLot(String parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getparkingLot() {
        return parkingLot;
    }

    public String getvehicleType() {
        return vehicleType;
    }

    public void setvehicleType(String vehicleType) {
        
        List<String> listVehicleType = new ArrayList<String>();
        listVehicleType.add("Car");
        listVehicleType.add("Batimovil");
        listVehicleType.add("Motorcycle");
        listVehicleType.add("Bicycle");
        listVehicleType.add("Disabled Parking");
        
        this.vehicleType = "";

        for (int i = 0; i < listVehicleType.size(); i = i + 1) {
            if (vehicleType.equalsIgnoreCase(listVehicleType.get(i))) {
                this.vehicleType = vehicleType;
                break;
            }
        }

        if (this.vehicleType == "") 
            throw new ReservationInvalidVehicleTypeException("Invalid vehicle type to update");
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        Date currentDate = new Date();

        if (entryTime.before(currentDate) || entryTime.equals(currentDate))
            throw new ReservationInvalidEntryTimeException("The entryTime must occur after current date");
        
        this.entryTime = entryTime;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        if (estimatedTime <= 0)           
            throw new ReservationInvalidEstimatedTimeException("The estimated time must be grather than 0");

        this.estimatedTime = estimatedTime;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(int estimatedTime) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(this.entryTime);
        calendar.add(Calendar.MINUTE, this.estimatedTime);

        this.exitTime = calendar.getTime();
    }
}