package com.hackerrank.api.service;

import com.hackerrank.api.exception.BadRequestException;
import com.hackerrank.api.exception.ElementNotFoundException;
import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import com.hackerrank.api.repository.CovidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@RequiredArgsConstructor
public class CovidService {

  private final CovidRepository covidRepository;

    /**
     * Creates a new Covid entry.
     *
     * @param covid object to be created.
     * @return covid created object.
     */
  public Covid createNewCovid(Covid covid) {
    if (covid.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Scan");
    }
    return covidRepository.save(covid);
  }

    /**
     * Gets covid by id.
     *
     * @param id id to look for.
     * @return Covid object with the given id from above.
     */
  public Covid getCovidById(Long id) {
    return covidRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFoundException("Covid by Id not found:" + id));
  }

    /**
     * Returns the top 5 covid entries sorted by a given field.
     *
     * @param by given field to be sorted by.
     * @return List of top 5 covid objects.
     */
  public List<Covid> top5By(String by) {
    try {
      Sort sort = Sort.by(Sort.Direction.DESC, by);
      return covidRepository.findTop5By(sort);
    } catch (Exception e) {
      throw new BadRequestException("Invalid attribute: " + by);
    }
  }

    /**
     * Returns the total entries by: active, death and recovered.
     *
     * @param by filter to be used: active, death and recovered.
     * @return total sum of entries by a given filter.
     */
  public Integer totalBy(String by) {
      return switch (by.toLowerCase()) {
          case "active" -> covidRepository.findAll().stream().mapToInt(Covid::getActive).sum();
          case "death" -> covidRepository.findAll().stream().mapToInt(Covid::getDeath).sum();
          case "recovered" -> covidRepository.findAll().stream().mapToInt(Covid::getRecovered).sum();
          default -> throw new BadRequestException("Invalid attribute: " + by);
      };
  }

    /**
     * Generates a report, indicating the Impact Factor.
     *
     * @return List of Report entries grouped by continent.
     */
  public List<Report> getReport() {
    List<Covid> covidList = covidRepository.findAll();

    return covidList.stream()
            .filter(covid -> covid.getContinent() != null && !covid.getContinent().isEmpty())
            .collect(groupingBy(Covid::getContinent))
            .entrySet()
            .stream()
            .map(entry -> new Report(entry.getKey(), computeImpactFactor(
                    entry.getValue().stream().mapToInt(Covid::getDeath).sum(),
                    entry.getValue().stream().mapToInt(Covid::getActive).sum(),
                    entry.getValue().stream().mapToInt(Covid::getRecovered).sum()
            ))).toList();
  }

  private Double computeImpactFactor(int death, int active, int recovered) {
      return BigDecimal.valueOf((double) death / (active + death + recovered))
        .setScale(3, RoundingMode.HALF_UP)
        .doubleValue();
  }
}
