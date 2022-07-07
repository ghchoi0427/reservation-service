package com.cgh.inflearn.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ViewReservationDto {

    private String userName;
    private String reservationId;
    private int date;
    private int month;
    private int year;
    private String startTime;
    private String endTime;
}
