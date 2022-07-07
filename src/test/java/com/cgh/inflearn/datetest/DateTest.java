package com.cgh.inflearn.datetest;

import com.cgh.inflearn.domain.Reservation;
import com.cgh.inflearn.repository.ReservationRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class DateTest {

    ReservationRepositoryImpl repository = new ReservationRepositoryImpl();

    @Test
    void 로컬데이트타임과로컬데이트() {
        LocalDate target = LocalDate.of(2022, 07, 06);
        LocalDateTime comparisonStart = LocalDateTime.of(2022, 07, 06, 23, 40, 9);
        LocalDateTime comparisonEnd = LocalDateTime.of(2022, 07, 06, 23, 40, 9);
        Reservation reservation = new Reservation(UUID.randomUUID(), comparisonStart, comparisonEnd);

        repository.save(reservation);

        Assertions.assertThat(repository.findByDate(target).get(0)).isEqualTo(reservation);

    }

    @Test
    void 포매터테스트() {
        String date = "2022-07-08";
        String time = "04";

        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        int hour = Integer.parseInt(time);

        System.out.println(LocalDateTime.of(year, month, day, hour, 0));
        System.out.println(LocalDateTime.of(2022, 7, 8, 4, 0));
        Assertions.assertThat(LocalDateTime.of(year, month, day, hour, 0)).isEqualTo(LocalDateTime.of(2022, 7, 8, 4, 0));
    }
}
