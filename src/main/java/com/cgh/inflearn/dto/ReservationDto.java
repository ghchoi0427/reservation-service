package com.cgh.inflearn.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReservationDto {

    private String userId;
    private String date;
    private String startTime;
    private String endTime;
}
