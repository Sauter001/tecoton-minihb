package com.sauter001.demo.spike.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/** 평면 모델. Reservation을 참조하므로 실습 7의 2단계 깊이 관측에 쓴다. */
@Entity
@Table(name = "reservation_wait")
public class ReservationWait extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private LocalDateTime createdAt;

    protected ReservationWait() {
    }

    public ReservationWait(Reservation reservation, Member member, LocalDateTime createdAt) {
        this.reservation = reservation;
        this.member = member;
        this.createdAt = createdAt;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Member getMember() {
        return member;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
