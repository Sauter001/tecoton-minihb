# 골든 테스트 정답지

하이버네이트로 아래 열 개를 실행하고 `org.hibernate.SQL` 로그에서 SQL을 떠서
`golden/mysql/01.sql`, `golden/postgres/01.sql` 처럼 저장한다. minihb 단계 5가 끝나면 붙일 수 있다.

| 번호 | 쿼리 |
| --- | --- |
| 01 | `SELECT r FROM Reservation r` |
| 02 | `SELECT r FROM Reservation r WHERE r.date = :date` |
| 03 | `SELECT r FROM Reservation r WHERE r.theme.name = :name` |
| 04 | `SELECT r FROM Reservation r WHERE r.theme.name = :name AND r.date >= :from` |
| 05 | 02번 + `setMaxResults` |
| 06 | `SELECT r FROM Reservation r JOIN FETCH r.theme` |
| 07 | `SELECT r FROM Reservation r JOIN FETCH r.waitings` |
| 08 | `SELECT c FROM SlotClaim c WHERE c.member.id = :id` (상속 3전략) |
| 09 | `SELECT c FROM SlotClaim c WHERE TYPE(c) = SlotWaiting` (여유가 되면) |
| 10 | `SELECT r.date, r.theme.name FROM Reservation r` (여유가 되면) |

비교 전 정규화 기준. 이 표를 정하는 일이 곧 판정 기준을 세우는 일이다.

| 차이 | 판정 | 처리 |
| --- | --- | --- |
| 별칭 이름 | 표기 | 정규화한다 |
| 공백과 줄바꿈 | 표기 | 정규화한다 |
| SELECT 절 컬럼 순서 | 표기에 가깝다 | 정렬 후 비교 |
| 파라미터 자리 표시 | 표기 | 정규화한다 |
| 조인 종류 (INNER vs LEFT) | 의미 | 정규화하지 않는다 |
| 조인 개수 | 의미 | 정규화하지 않는다 |
| WHERE 절 괄호 구조 | 의미 | 정규화하지 않는다 |

100퍼센트 일치는 목표가 아니다. 어디까지 일치하고 어디서부터 갈라지는지, 그 경계가 학습 내용이다.
