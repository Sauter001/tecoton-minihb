/**
 * 관측용 실험 도메인(roomescape 구조를 옮긴 것).
 *
 * <p>모델이 두 벌이다. 하나로 합치면 안 된다.
 * <ul>
 *   <li>평면 모델: {@link com.sauter001.demo.spike.domain.Reservation},
 *       {@link com.sauter001.demo.spike.domain.ReservationWait}. 실습 1, 2, 3, 5, 7, 8에서 쓴다.
 *       상속 전략을 바꿔도 SQL이 흔들리지 않아야 비교 기준선이 된다.</li>
 *   <li>상속 모델: {@link com.sauter001.demo.spike.domain.SlotClaim} 계층. 실습 4, 6에서
 *       {@code @Inheritance} 전략만 바꿔가며 관측한다.</li>
 * </ul>
 *
 * <p>참조 엔티티({@code Member}, {@code Theme}, {@code ReservationTime}, {@code Store})는 두 모델이 공유한다.
 *
 * <p>규칙: Lombok {@code @Data}/{@code @ToString} 금지, toString에 연관 필드 금지,
 * equals/hashCode 재정의 금지, 기본 생성자는 protected, {@code @ManyToOne}은 전부 LAZY 명시.
 */
package com.sauter001.demo.spike.domain;
