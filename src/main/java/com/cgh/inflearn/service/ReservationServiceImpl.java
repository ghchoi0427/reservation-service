package com.cgh.inflearn.service;

import com.cgh.inflearn.domain.Reservation;
import com.cgh.inflearn.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository repository;

    @Override
    public Reservation findReservation(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return repository.findAll();
    }

    @Override
    public void save(Reservation reservation) {
        repository.save(reservation);
    }

    @Override
    public void delete(UUID uuid) {
        repository.delete(uuid);
    }
}
