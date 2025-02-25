package com.covid.api.model;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CovidMapper {

    Covid toCovid(CovidDto covidDto);

    CovidDto toCovidDto(Covid covid);

    List<CovidDto> toCovidDtoList(List<Covid> covidList);
}
