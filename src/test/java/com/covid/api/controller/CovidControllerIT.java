package com.covid.api.controller;

import com.covid.api.configuration.BaseSpringBootIntegrationTest;
import com.covid.api.model.CovidDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

//TODO: Implement missing tests
class CovidControllerIT extends BaseSpringBootIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetCovidById() {
        //given
        int id = 1;

        //when //TODO: Change to response entity, and check the status code as well
        CovidDto result = restTemplate.getForObject(
                "/api/covid/byId/" + id,
                CovidDto.class
        );

        //then
        assertThat(result).isNotNull();
        assertThat(result.country()).isEqualTo("Argentina");

    }

    @Test
    void createCovidEntry() {
    }

    @Test
    void getTop5SortedBy() {
    }

    @Test
    void getTotalBy() {
    }

    @Test
    void getReport() {
    }
}