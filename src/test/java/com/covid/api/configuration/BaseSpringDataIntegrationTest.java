package com.covid.api.configuration;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

// Class used to integration test Repositories with testcontainers.
// This shouldn't be used always, just whenever we want to quickly test custom queries.
@SpringDataIntegrationTest
public abstract class BaseSpringDataIntegrationTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.0");
}
