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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
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

            dto.setUserName(memberService.findById(reservation.getUserId()).getName());
            dto.setDate(date);
            dto.setMonth(month);
            dto.setYear(year);
            dto.setReservationId(reservation.getId().toString());

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
    public String viewForm(@CookieValue(required = false) String memberId) {
        if (memberId == null) {
            return "redirect:/home/login";
        }
        return "reservation-form";
    }

    @PostMapping("/new")
    public String make(ReservationDto dto, @CookieValue String memberId) {
        //TODO: UUID 수정
        Reservation reservation = new Reservation(UUID.fromString(memberId),
                dateTimeFormatter(dto.getDate(), dto.getStartTime()),
                dateTimeFormatter(dto.getDate(), dto.getEndTime()));
        reservation.setUserId(UUID.fromString(memberId));
        reservationService.save(reservation);
        return "redirect:/reservation/view-list";
    }

    @GetMapping("/{reservationDate}")
    public ResponseEntity<List<ViewReservationDto>> reservationDetailByDate(@PathVariable String reservationDate) {
        int year = Integer.parseInt(reservationDate.split("-")[0]);
        int month = Integer.parseInt(reservationDate.split("-")[1]);
        int day = Integer.parseInt(reservationDate.split("-")[2]);
        List<Reservation> reservations = reservationService.findReservationByDate(LocalDate.of(year, month, day));
        List<ViewReservationDto> dtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ViewReservationDto dto = new ViewReservationDto();
            String username = memberService.findById(reservation.getUserId()).getName();
            dto.setReservationId(reservation.getId().toString());
            dto.setUserName(username);
            dto.setDate(reservation.getStartTime().toLocalDate().getDayOfMonth());
            dto.setMonth(reservation.getStartTime().getMonthValue());
            dto.setYear(reservation.getStartTime().getYear());
            dto.setStartTime(reservation.getStartTime().toString());
            dto.setEndTime(reservation.getEndTime().toString());
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{reservationDate}/detail")
    public String viewReservationDetailByDate(@PathVariable String reservationDate, HttpServletResponse response) {
        Cookie resCookie = new Cookie("reservationDate", reservationDate);
        resCookie.setPath("/");
        response.addCookie(resCookie);
        return "reservation-detail";
    }

//    @GetMapping("/{reservationId}")
//    public ResponseEntity<ViewReservationDto> reservationDetail(@PathVariable UUID reservationId) {
//        Reservation reservation = reservationService.findReservation(reservationId);
//        ViewReservationDto dto = new ViewReservationDto();
//        String username = memberService.findById(reservation.getUserId()).getName();
//        dto.setUserName(username);
//        dto.setDate(reservation.getStartTime().toLocalDate().getDayOfMonth());
//        dto.setMonth(reservation.getStartTime().getMonthValue());
//        dto.setYear(reservation.getStartTime().getYear());
//        dto.setStartTime(reservation.getStartTime().toString());
//        dto.setEndTime(reservation.getEndTime().toString());
//
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//    @GetMapping("/{reservationId}/detail")
//    public String viewReservationDetail(@PathVariable UUID reservationId, HttpServletResponse response) {
//        Cookie resCookie = new Cookie("reservationId", reservationId.toString());
//        resCookie.setPath("/");
//        response.addCookie(resCookie);
//
//        return "reservation-detail";
//    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<List<Reservation>> delete(@PathVariable String reservationId) {
        reservationService.delete(UUID.fromString(reservationId));
        List<Reservation> reservationList = reservationService.findAllReservations();
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

    private LocalDateTime dateTimeFormatter(String date, String time) {
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        int hour = Integer.parseInt(time);

        return LocalDateTime.of(year, month, day, hour, 0);
    }
}
