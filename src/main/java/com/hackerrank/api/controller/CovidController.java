package com.hackerrank.api.controller;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import com.hackerrank.api.service.CovidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: Add comments
@Slf4j
@RestController
@RequestMapping("/covid")
public class CovidController {
  private final CovidService covidService;

  //TODO: Replace this with lombok
  @Autowired
  public CovidController(CovidService covidService) {
    this.covidService = covidService;
  }

  @GetMapping("/byId/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Covid getCovidById(@PathVariable Long id) {
    return covidService.getCovidById(id);
  }

  /**
   * return the top 5 covid entries sorted by given field and status code 200.
   *
   * @param by  for example: `/covid/top5?by=death` gives total deaths.
   * @return If `by` is an invalid attribute, return status code 400.
   */
  @GetMapping("/top5")
  @ResponseStatus(HttpStatus.OK)
  public List<Covid> getTop5SortedBy(@RequestParam String by) {
    log.info("Rest controller getCovidBy: {}", by);

    return this.covidService.top5By(by);
  }

  @GetMapping("/total")
  @ResponseStatus(HttpStatus.OK)
  public Integer getTotalBy(@RequestParam String by) {
    log.info("Rest controller getTotalBy: {}", by);
    return this.covidService.totalBy(by);
  }

  @GetMapping("/scan/report/scanDashboard")
  @ResponseStatus(HttpStatus.OK)
  public List<Report> getReport() {
    log.info("Getting report!");
    return this.covidService.getReport();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Covid createCovid(@RequestBody Covid covid) {
    return covidService.createNewCovid(covid);
  }
}
