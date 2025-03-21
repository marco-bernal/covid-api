package com.covid.api.configuration;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

/**
 *  Base class for Integration Tests.
 */
@SpringBootIntegrationTest
public abstract class BaseSpringBootIntegrationTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.0");
}
