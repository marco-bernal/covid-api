package com.hackerrank.api.controller;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/covid")
public class CovidController {
  private final CovidService covidService;

  @Autowired
  public CovidController(CovidService covidService) {
    this.covidService = covidService;
  }

  @GetMapping("/byId/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Covid> getCovidById(@PathVariable Long id) {
    return new ResponseEntity<>(this.covidService.getCovidById(id), HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Covid createCovid(@RequestBody Covid covid) {
    return covidService.createNewCovid(covid);
  }
}
