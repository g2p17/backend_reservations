package com.parkingWeb.reservations.repositories;

import com.parkingWeb.reservations.models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByParkingLot (String parkingLot);

    /*
    "clientId" : 3,
    "parkingLot" : "Colorado",
    "vehicleType" : "Car",
    "entryTime" : "2021-13-09T07:00:00.405Z",
    "estimatedTime" : 120,
    "vehiclePlate" : "A7D401"
    */
}
