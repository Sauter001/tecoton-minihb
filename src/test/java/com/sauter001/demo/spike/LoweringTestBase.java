package com.sauter001.demo.spike;

import static org.assertj.core.api.Assertions.assertThat;

import com.sauter001.demo.spike.domain.Member;
import com.sauter001.demo.spike.domain.Reservation;
import com.sauter001.demo.spike.domain.ReservationTime;
import com.sauter001.demo.spike.domain.ReservationWait;
import com.sauter001.demo.spike.domain.Role;
import com.sauter001.demo.spike.domain.SlotClaim;
import com.sauter001.demo.spike.domain.SlotReservation;
import com.sauter001.demo.spike.domain.SlotWaiting;
import com.sauter001.demo.spike.domain.Store;
import com.sauter001.demo.spike.domain.Theme;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * 실습 0의 기본 골격. 컨테이너만 갈아끼운 자식 둘이 같은 쿼리를 돌린다.
 *
 * <p>조건이 하나만 달라진다는 걸 코드로 보장하려고 쿼리를 부모에 둔다.
 * 테스트 하나에 쿼리 하나만 넣고 앞뒤에 구분선을 찍어야 로그에서 캡처할 구간이 보인다.
 *
 * <p>{@code @Transactional}이 자식이 아니라 여기 붙어 있는 이유. Spring 7은 트랜잭션 속성을
 * 테스트 메서드 기준으로 찾기 때문에, 메서드를 선언한 클래스에 붙어 있어야 한다. 자식에만 붙이면
 * 조회는 그냥 통과하고 persist에서 TransactionRequiredException으로 터진다.
 */
@Transactional
abstract class LoweringTestBase {

    @PersistenceContext
    EntityManager em;

    @Test
    void 페이징_쿼리는_어떤_SQL로_나가는가() {
        System.out.println("=== 여기부터 ===");

        em.createQuery("SELECT r FROM Reservation r WHERE r.theme.name = :name", Reservation.class)
                .setParameter("name", "공포")
                .setMaxResults(10)
                .getResultList();

        System.out.println("=== 여기까지 ===");
    }

    /**
     * 환경 점검용. 두 모델 다 저장과 조회가 되는지, SEQUENCE 전략이 두 DB에서 다 도는지 본다.
     * MySQL은 시퀀스를 테이블로 흉내내므로 여기서 한 번 확인해 두면 실습 4에서 놀랄 일이 없다.
     */
    @Test
    void 두_모델_다_저장하고_읽을_수_있다() {
        Store store = new Store("강남점");
        Theme theme = new Theme("공포", "많이 무섭다");
        ReservationTime time = new ReservationTime(LocalTime.of(10, 0));
        Member member = new Member("사우터", Role.USER);
        em.persist(store);
        em.persist(theme);
        em.persist(time);
        em.persist(member);

        Reservation reservation = new Reservation(LocalDate.of(2026, 1, 1), member, time, theme, store);
        em.persist(reservation);
        em.persist(new ReservationWait(reservation, member, LocalDateTime.of(2026, 1, 1, 9, 0)));

        em.persist(new SlotReservation(LocalDate.of(2026, 1, 1), member, theme, time, store));
        em.persist(new SlotWaiting(LocalDate.of(2026, 1, 1), member, theme, time, store,
                LocalDateTime.of(2026, 1, 1, 9, 0)));

        em.flush();
        em.clear();

        assertThat(reservation.getId()).isNotNull();
        assertThat(em.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList()).hasSize(1);
        assertThat(em.createQuery("SELECT w FROM ReservationWait w", ReservationWait.class).getResultList()).hasSize(1);
        assertThat(em.createQuery("SELECT c FROM SlotClaim c", SlotClaim.class).getResultList()).hasSize(2);
    }
}
