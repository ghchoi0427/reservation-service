package com.cgh.inflearn.repository;

import com.cgh.inflearn.domain.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    Reservation findById(UUID id);

    List<Reservation> findAll();

    List<Reservation> findByDate(LocalDate date);

    void save(Reservation reservation);

    void delete(UUID reservationId);
}
