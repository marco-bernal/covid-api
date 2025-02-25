package com.covid.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CovidDto {
    private String country;
    private String continent;
    private Integer confirmed;
    private Integer death;
    private Integer recovered;
    private Integer active;
}
