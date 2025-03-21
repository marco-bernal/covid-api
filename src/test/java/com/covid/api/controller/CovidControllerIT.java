package com.covid.api.controller;

import com.covid.api.configuration.BaseSpringBootIntegrationTest;
import com.covid.api.model.CovidDto;
import com.covid.api.model.Report;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional //Used to rollback insertions, deletions and updates with the @Rollback annotation.
class CovidControllerIT extends BaseSpringBootIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Rollback
    void createCovidEntry() {
        //given
        CovidDto covidDto = buildCovidDto();

        //when
        ResponseEntity<CovidDto> response = restTemplate.exchange(
                "/api/covid",
                HttpMethod.POST,
                new HttpEntity<>(covidDto),
                CovidDto.class
        );

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().country()).isEqualTo("Finland");
        assertThat(response.getBody().continent()).isEqualTo("Europe");
        assertThat(response.getBody().confirmed()).isEqualTo(1000);
        assertThat(response.getBody().death()).isEqualTo(30);
        assertThat(response.getBody().recovered()).isEqualTo(820);
        assertThat(response.getBody().active()).isEqualTo(150);
    }

    @Test
    void shouldGetCovidById() {
        //given
        int id = 1;

        //when
        ResponseEntity<CovidDto> response = restTemplate.exchange(
                "/api/covid/byId/" + id,
                HttpMethod.GET,
                null,
                CovidDto.class
        );

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().country()).isEqualTo("Argentina");
        assertThat(response.getBody().continent()).isEqualTo("America");
        assertThat(response.getBody().confirmed()).isEqualTo(1600710);
        assertThat(response.getBody().death()).isEqualTo(30847);
        assertThat(response.getBody().recovered()).isEqualTo(8553);
        assertThat(response.getBody().active()).isEqualTo(75356);
    }

    @Test
    void shouldReturnStatus404WhenCovidIdNotFound() {
        //given
        int id = 11;

        ResponseEntity<CovidDto> response = restTemplate.exchange(
                "/api/covid/byId/" + id,
                HttpMethod.GET,
                null,
                CovidDto.class
        );

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getTop5CountriesByDeath() {
        //when
        ResponseEntity<List<CovidDto>> response = restTemplate.exchange(
                "/api/covid/top5?by=death",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        //TODO: Assert that contains exactly these countries: USA, Nigeria, Italy, China, Russia.
        // Add the number of deaths as well.
    }

    @Test
    void getTotalActiveCases() {
        //given
        ResponseEntity<Integer> response = restTemplate.exchange(
                "/api/covid/total?by=active",
                HttpMethod.GET,
                null,
                Integer.class
        );

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(34497920);
    }

    @Test
    void getReport() {
        //when
        ResponseEntity<List<Report>> response = restTemplate.exchange(
                "/api/covid/report",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        //TODO: Assert that contains exactly these continents and values:
        // Report(continent=Asia, impactFactor=0.108)
        // Report(continent=Europe, impactFactor=0.288)
        // Report(continent=Africa, impactFactor=0.216)
        // Report(continent=America, impactFactor=0.224)
    }

    private CovidDto buildCovidDto() {
        return CovidDto.builder()
                .country("Finland")
                .continent("Europe")
                .confirmed(1000)
                .death(30)
                .recovered(820)
                .active(150)
                .build();
    }
}