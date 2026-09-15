package com.sauter001.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * 환경이 다 됐는지 보는 첫 번째 기준. 상속 전략을 TABLE_PER_CLASS로 바꿔도 이 테스트가 통과해야 한다.
 * (두 번째 기준인 "SQM 덤프 -> SQL AST 덤프 -> 최종 SQL"은 LoweringTestBase 쪽 로그에서 확인한다)
 */
@SpringBootTest
@Testcontainers
class MiniHibernateApplicationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer db = new PostgreSQLContainer("postgres:16");

    @Test
    void contextLoads() {
    }
}
