package com.sauter001.demo.spike.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "reservation_time")
public class ReservationTime extends BaseEntity {

    private LocalTime startAt; // 원본은 VARCHAR였다. Dialect별 시간 처리를 보려면 반드시 LocalTime

    protected ReservationTime() {
    }

    public ReservationTime(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getStartAt() {
        return startAt;
    }
}
