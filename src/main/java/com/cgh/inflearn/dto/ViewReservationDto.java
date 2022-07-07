package com.cgh.inflearn.dto;

import lombok.Data;

@Data
public class ViewReservationDto {

    private String userName;
    private int date;
    private int month;
    private int year;
    private String startTime;
    private String endTime;
}
