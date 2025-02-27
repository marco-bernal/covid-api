package com.covid.api.controller;

import com.covid.api.model.CovidDto;
import com.covid.api.model.Report;
import com.covid.api.service.CovidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO; Add controller advice and return exception's root cause as an Error object.
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/covid")
public class CovidController {

  private final CovidService covidService;

  /**
   * Creates a new Covid entry.
   *
   * @param covidDto object to be created.
   * @return covid created object.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CovidDto createCovidEntry(@RequestBody CovidDto covidDto) {
    log.info("createCovid: {}", covidDto);
    return covidService.createNewCovid(covidDto);
  }

  /**
   * Gets covid by id.
   *
   * @param id id to look for.
   * @return Covid object with the given id from above.
   */
  @GetMapping("/byId/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CovidDto getCovidById(@PathVariable Long id) {
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
  public List<CovidDto> getTop5SortedBy(@RequestParam String by) {
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
