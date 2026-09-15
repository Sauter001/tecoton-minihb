package com.sauter001.demo.spike;

import com.sauter001.demo.spike.domain.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;

/**
 * 실습 0의 기본 골격. 컨테이너만 갈아끼운 자식 둘이 같은 쿼리를 돌린다.
 *
 * <p>조건이 하나만 달라진다는 걸 코드로 보장하려고 쿼리를 부모에 둔다.
 * 테스트 하나에 쿼리 하나만 넣고 앞뒤에 구분선을 찍어야 로그에서 캡처할 구간이 보인다.
 */
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
}
