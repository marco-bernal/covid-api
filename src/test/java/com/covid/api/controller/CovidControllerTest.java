package com.covid.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class CovidControllerTest {

  @Test
  void testCreation() {
    //placeholder test to trigger db script
    Assertions.assertEquals(1, 1);
  }

  //TODO:
  // * Switch from H2 to Flyway
  // * Refactor/Rename folders/code (delete .eval folder and any pre existent garbage files).
  // *
  // * Create meta annotation for IT.
  // * Add test containers support for DB IT.
  // * Create IT for Repo, Service and Controller Layers.
  // * Set up / configure Jacoco for code coverage (100%).
  // * Delete me.
  // *
  // * Add docker support.
  // * Add github actions.
}