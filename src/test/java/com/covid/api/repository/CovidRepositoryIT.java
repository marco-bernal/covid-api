package com.covid.api.repository;

import com.covid.api.configuration.BaseSpringDataIntegrationTest;
import com.covid.api.model.Covid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CovidRepositoryIT extends BaseSpringDataIntegrationTest {

    @Autowired
    private CovidRepository covidRepository;

    @Test
    void testConnection() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void shouldRetrieveCovidEntry() {
        //given
        Covid covid = new Covid();

        //when
        Optional<Covid> covidOptional = covidRepository.findById(1L);

        if (covidOptional.isPresent()) {
            covid = covidOptional.get();
        }

        //then
        assertThat(covidOptional).isPresent();
        assertThat(covid.getCountry()).isEqualTo("Argentina");
        assertThat(covid.getContinent()).isEqualTo("America");
        assertThat(covid.getConfirmed()).isEqualTo(1600710);
        assertThat(covid.getDeath()).isEqualTo(30847);
        assertThat(covid.getRecovered()).isEqualTo(8553);
        assertThat(covid.getActive()).isEqualTo(75356);
    }
}