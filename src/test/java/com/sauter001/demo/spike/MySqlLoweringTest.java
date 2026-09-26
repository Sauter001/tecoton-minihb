package com.sauter001.demo.spike;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

@SpringBootTest
@Testcontainers
class MySqlLoweringTest extends LoweringTestBase {

    @Container
    @ServiceConnection
    static MySQLContainer db = new MySQLContainer("mysql:8.4");
}
