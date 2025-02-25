package com.covid.api.model;

import lombok.*;

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
