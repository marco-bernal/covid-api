package com.covid.api.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CovidDto(
        @NotEmpty
        String country,
        @NotEmpty
        String continent,
        Integer confirmed,
        Integer death,
        Integer recovered,
        Integer active
) {
}