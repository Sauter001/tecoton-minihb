package com.sauter001.demo.spike.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("WAITING")
public class SlotWaiting extends SlotClaim {

    // SINGLE_TABLE에서는 nullable 컬럼이 된다. 그 자체가 SINGLE_TABLE의 대가를 보여주는 재료다(과제 Q9)
    private LocalDateTime createdAt;

    protected SlotWaiting() {
    }

    public SlotWaiting(LocalDate date, Member member, Theme theme, ReservationTime time, Store store,
                       LocalDateTime createdAt) {
        super(date, member, theme, time, store);
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
