package com.cgh.inflearn.controller;

import com.cgh.inflearn.domain.Reservation;
import com.cgh.inflearn.dto.ReservationDto;
import com.cgh.inflearn.dto.ViewReservationDto;
import com.cgh.inflearn.service.MemberService;
import com.cgh.inflearn.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<List<ViewReservationDto>> list() {
        List<ViewReservationDto> dtos = new ArrayList<>();
        List<Reservation> reservationList = reservationService.findAllReservations();
        for (Reservation reservation : reservationList) {
            int year = Integer.parseInt(reservation.getStartTime().toLocalDate().toString().split("-")[0]);
            int month = Integer.parseInt(reservation.getStartTime().toLocalDate().toString().split("-")[1]);
            int date = Integer.parseInt(reservation.getStartTime().toLocalDate().toString().split("-")[2]);

            ViewReservationDto dto = new ViewReservationDto();
            System.out.println(memberService.findById(reservation.getUserId()));

            dto.setUserName(memberService.findById(reservation.getUserId()).getName());
            dto.setDate(date);
            dto.setMonth(month);
            dto.setYear(year);

            dto.setStartTime(reservation.getStartTime().toString());
            dto.setEndTime(reservation.getEndTime().toString());
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/view-list")
    public String viewList() {
        return "view-list";
    }

    @GetMapping("/new")
    public String viewForm() {

        return "reservation-form";
    }

    @PostMapping("/new")
    public String make(ReservationDto dto, @CookieValue String memberId) {
        //TODO: UUID 수정
        Reservation reservation = new Reservation(UUID.fromString(memberId),
                dateTimeFormatter(dto.getDate(), dto.getStartTime()),
                dateTimeFormatter(dto.getDate(), dto.getEndTime()));
        reservationService.save(reservation);
        return "redirect:/reservation/view-list";
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> reservationDetail(@PathVariable UUID reservationId) {
        Reservation reservation = reservationService.findReservation(reservationId);
        ReservationDto dto = new ReservationDto();
        dto.setUserId(reservation.getUserId().toString());
        dto.setStartTime(reservation.getStartTime().toString());
        dto.setEndTime(reservation.getEndTime().toString());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{reservationId}")
    public String delete(@PathVariable UUID reservationId) {
        reservationService.delete(reservationId);
        return "redirect:/reservation/list";
    }

    private LocalDateTime dateTimeFormatter(String date, String time) {
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        int hour = Integer.parseInt(time);

        return LocalDateTime.of(year, month, day, hour, 0);
    }
}
