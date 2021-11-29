package com.parkingWeb.reservations.repositories;

import com.parkingWeb.reservations.models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ReservationRepository extends MongoRepository<Reservation, String> {
}