package com.parkingWeb.reservations.repositories;

import com.parkingWeb.reservations.models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByclientId (Integer clientId);
}
