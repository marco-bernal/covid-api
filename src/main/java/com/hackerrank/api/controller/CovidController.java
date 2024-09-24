package com.hackerrank.api.controller;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import com.hackerrank.api.service.CovidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/covid")
@RequiredArgsConstructor
public class CovidController {

  private final CovidService covidService;

  /**
   * Creates a new Covid entry.
   *
   * @param covid object to be created.
   * @return covid created object.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Covid createCovid(@RequestBody Covid covid) {
    log.info("createCovid: {}", covid);
    return covidService.createNewCovid(covid);
  }

  /**
   * Gets covid by id.
   *
   * @param id id to look for.
   * @return Covid object with the given id from above.
   */
  @GetMapping("/byId/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Covid getCovidById(@PathVariable Long id) {
    log.info("getCovidById: {}", id);
    return covidService.getCovidById(id);
  }

  /**
   * Returns the top 5 covid entries sorted by a given field.
   *
   * @param by given field to be sorted by.
   * @return List of top 5 covid objects.
   */
  @GetMapping("/top5")
  @ResponseStatus(HttpStatus.OK)
  public List<Covid> getTop5SortedBy(@RequestParam String by) {
    log.info("getTop5SortedBy: {}", by);
    return covidService.top5By(by);
  }

  /**
   * Returns the total entries by: active, death and recovered.
   *
   * @param by filter to be used: active, death and recovered.
   * @return total sum of entries by a given filter.
   */
  @GetMapping("/total")
  @ResponseStatus(HttpStatus.OK)
  public Integer getTotalBy(@RequestParam String by) {
    log.info("Rest controller getTotalBy: {}", by);
    return covidService.totalBy(by);
  }

  /**
   * Generates a report, indicating the Impact Factor.
   *
   * @return List of Report entries grouped by continent.
   */
  @GetMapping("/report")
  @ResponseStatus(HttpStatus.OK)
  public List<Report> getReport() {
    log.info("Getting report!");
    return covidService.getReport();
  }
}
