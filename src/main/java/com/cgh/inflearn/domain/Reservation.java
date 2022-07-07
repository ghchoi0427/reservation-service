package com.cgh.inflearn.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Reservation {

    private UUID id;
    private UUID userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Reservation(UUID userId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
