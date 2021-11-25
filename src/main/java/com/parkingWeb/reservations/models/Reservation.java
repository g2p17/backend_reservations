package com.parkingWeb.reservations.models;

import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Reservation {

    @Id
    private String  id;
    private Long    clientId;
    private String  parkingLot;
    private String  vehicleType;
    private Date    arrivalDate;
    private String  arrivalHour;
    private int     estimatedTime;
    private String  vehiclePlate;

    public Reservation(String id, Long clientId, String parkingLot, String vehicleType, 
        Date arrivalDate, String arrivalHour, int estimatedTime, String vehiclePlate) throws Exception {
        this.id = id;
        this.clientId = clientId;
        this.parkingLot = parkingLot;
        this.setvehicleType(vehicleType);
        this.setArrivalDate(arrivalDate);
        this.setEstimatedTime(estimatedTime);
        this.vehiclePlate = vehiclePlate;
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

    public Date getarrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) throws Exception {
        //"2012-12-19T06:01:17.171Z"        
        this.arrivalDate = null;

        try {
            Date currentDate = new Date();
            if (arrivalDate.compareTo(currentDate) > 0) {

                DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss Z");
                String strDate = formatter.format(arrivalDate);            
                
                this.arrivalDate = arrivalDate;
                setArrivalHour(strDate.split(" ")[2]);
            }
        } catch (Exception ex) {
            throw new Exception("arrivalDate must occur after current date");
        }
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;       
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
}