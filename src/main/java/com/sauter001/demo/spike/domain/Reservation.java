package com.sauter001.demo.spike.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** 평면 모델. 실습 1, 2, 3, 5, 7, 8이 쓴다. */
@Getter
@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity {

    private LocalDate date; // 원본은 VARCHAR였다. 반드시 LocalDate

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationTime time;

    @ManyToOne(fetch = FetchType.LAZY) // 실습 7에서 이 줄을 EAGER로 토글한다
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    // 컬렉션은 이것 하나만 둔다. 둘 이상이면 DomainResult 덤프를 읽을 수 없다
    @OneToMany(mappedBy = "reservation")
    private List<ReservationWait> waitings = new ArrayList<>();

    protected Reservation() {
    }

    public Reservation(LocalDate date, Member member, ReservationTime time, Theme theme, Store store) {
        this.date = date;
        this.member = member;
        this.time = time;
        this.theme = theme;
        this.store = store;
    }

}
