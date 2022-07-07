package com.cgh.inflearn.service;

import com.cgh.inflearn.domain.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationService {

    Reservation findReservation(UUID id);

    List<Reservation> findAllReservations();

    void save(Reservation reservation);

    void delete(UUID uuid);

    List<Reservation> findReservationByDate(LocalDate date);
}
