package com.cgh.inflearn.reservation;

import com.cgh.inflearn.domain.Reservation;
import com.cgh.inflearn.repository.ReservationRepositoryImpl;
import com.cgh.inflearn.service.ReservationService;
import com.cgh.inflearn.service.ReservationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationTest {

    private final ReservationService reservationService = new ReservationServiceImpl(new ReservationRepositoryImpl());



    @Test
    void 예약테스트() {
        Reservation reservation = new Reservation(UUID.randomUUID(), LocalDateTime.MIN, LocalDateTime.now());
        reservationService.save(reservation);

        Reservation reservation2 = reservationService.findReservation(reservation.getId());

        Assertions.assertThat(reservation).isEqualTo(reservation2);
    }
}
