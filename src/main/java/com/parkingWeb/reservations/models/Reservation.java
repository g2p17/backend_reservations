package com.parkingWeb.reservations.models;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Reservation {

    @Id
    private String  id;
    private Long    clientId;
    private String  parkingLot;
    private String  vehicleType;
    private Date    entryTime;
    private Date    exitTime;
    private int     estimatedTime;
    private String  vehiclePlate;


    public Reservation(String id, Long clientId, String parkingLot, String vehicleType, 
        Date entryTime, int estimatedTime, String vehiclePlate) throws Exception {
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

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
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
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) throws Exception {
        Date currentDate = null;
        this.entryTime = null;

        try {
            currentDate = new Date();

            if (entryTime.after(currentDate)) {         
                this.entryTime = entryTime;
            }
        } catch (Exception ex) {
            throw new Exception("entryTime must occur after current date");
        }
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) throws Exception {
        if (estimatedTime <= 0)           
            throw new Exception("the estimated time must be grather than 0");

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