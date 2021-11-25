package com.parkingWeb.reservations.models;

import org.springframework.data.annotation.Id;
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
    private Date    arrivalHour;
    private int     estimatedTime;
    private String  vehiclePlate;

    public Reservation(String id, Long clientId, String parkingLot, String vehicleType) {
        this.id = id;
        this.clientId = clientId;
        this.parkingLot = parkingLot;
        this.setvehicleType(vehicleType);
    };

    public String getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getparkingLots() {
        return parkingLot;
    }

    public String getvehicleType() {
        return vehicleType;
    }

    public void setvehicleType(String vehicleType) {
        List<String> listVehicleType = new ArrayList<String>();
            listVehicleType.add("Automovil");
            listVehicleType.add("Batimovil");
            listVehicleType.add("Motocicleta");
            listVehicleType.add("Bicicleta");
            listVehicleType.add("Vehiculo persona con discapacidad");
        
        this.vehicleType = "";

        for (int i = 0; i < listVehicleType.size(); i = i + 1) {
            if (vehicleType == listVehicleType.get(i)) {
                this.vehicleType = vehicleType;
                break;
            }
        }
    }

    public Date getarrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date setArrivalHour() {
        return arrivalHour;
    }

    public void setarrivalHour(Date arrivalHour) {
        
        this.arrivalHour= null;
        Date date_reservation = new Date(System.currentTimeMillis());
        /*
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss Z");
        formatter.format(date_reservation);
                if(date_reservation > arrivalHour){
            this.arrivalHour = arrivalHour;
            break;    
        }*/        
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    //public void setTiempoEstimado(int tiempoEstimado) {
    //   this.tiempoEstimado = tiempoEstimado;
    //}

    public String getPlacaVehiculo() {
        return vehiclePlate;
    }

    public void setPlacaVehiculo(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }
}