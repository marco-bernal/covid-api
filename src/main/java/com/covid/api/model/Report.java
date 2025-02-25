package com.covid.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Report {
  private String continent;
  private Double impactFactor;
}
