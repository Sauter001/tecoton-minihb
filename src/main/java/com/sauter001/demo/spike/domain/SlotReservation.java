package com.sauter001.demo.spike.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("RESERVATION")
public class SlotReservation extends SlotClaim {

    protected SlotReservation() {
    }

    public SlotReservation(LocalDate date, Member member, Theme theme, ReservationTime time, Store store) {
        super(date, member, theme, time, store);
    }
}
