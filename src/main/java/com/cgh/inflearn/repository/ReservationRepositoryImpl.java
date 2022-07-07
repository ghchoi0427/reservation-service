package com.cgh.inflearn.repository;

import com.cgh.inflearn.domain.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<UUID, Reservation> map = new HashMap<>();

    @Override
    public Reservation findById(UUID id) {
        return map.get(id);
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Reservation> findByDate(LocalDate date) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : map.values()) {
            if (reservation.getStartTime().toLocalDate().equals(date)) {
                result.add(reservation);
            }
        }
        return result;
    }

    @Override
    public void save(Reservation reservation) {
        map.put(reservation.getId(), reservation);
    }

    @Override
    public void delete(UUID reservationId) {
        map.remove(reservationId);
    }

}
