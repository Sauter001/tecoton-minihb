package com.sauter001.demo.spike.domain;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 상속 모델. 실습 4와 6이 쓴다.
 *
 * <p>실습 4에서는 아래 {@code strategy} 한 줄만 SINGLE_TABLE / JOINED / TABLE_PER_CLASS로 바꾼다.
 * 나머지는 손대지 않아야 무엇 때문에 SQL이 바뀌었는지 헷갈리지 않는다.
 *
 * <p>자식에 {@code @Table}을 달지 않은 이유. Hibernate 7은 SINGLE_TABLE 계층의 자식에 {@code @Table}이
 * 붙어 있으면 부팅을 거부한다(AnnotationException: 루트가 테이블 매핑을 선언한다). 어차피 기본 네이밍
 * 전략이 SlotReservation을 slot_reservation으로 내려주므로, 애노테이션을 빼두면 JOINED와
 * TABLE_PER_CLASS에서도 같은 테이블 이름이 나오고 전략 한 줄만 바꾸는 성질이 유지된다.
 * {@code @DiscriminatorColumn}은 SINGLE_TABLE에서만 의미가 있고 다른 전략에서는 무시되므로 그대로 둔다.
 */
@Getter
@Entity
@Table(name = "slot_claim")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "claim_type")
public abstract class SlotClaim extends BaseEntity {

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    protected SlotClaim() {
    }

    protected SlotClaim(LocalDate date, Member member, Theme theme, ReservationTime time, Store store) {
        this.date = date;
        this.member = member;
        this.theme = theme;
        this.time = time;
        this.store = store;
    }

}
