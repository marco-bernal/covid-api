package com.hackerrank.api.service.impl;

import com.hackerrank.api.exception.BadRequestException;
import com.hackerrank.api.exception.ElementNotFoundException;
import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import com.hackerrank.api.repository.CovidRepository;
import com.hackerrank.api.service.CovidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class DefaultCovidService implements CovidService {
  private final CovidRepository covidRepository;

  //TODO: Inject with lombok instead
  @Autowired
  DefaultCovidService(CovidRepository covidRepository) {
    this.covidRepository = covidRepository;
  }

  @Override
  public List<Covid> getAllCovid() {
    return covidRepository.findAll();
  }

  //TODO: Add comments to all public methods
  @Override
  public Covid createNewCovid(Covid covid) {
    if (covid.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Scan");
    }

    return covidRepository.save(covid);
  }

  @Override
  public Covid getCovidById(Long id) {
    return covidRepository.findById(id)
                    .orElseThrow(() -> new ElementNotFoundException("Covid by Id not found:" + id));
  }

  @Override
  public List<Covid> top5By(String by) {
    try {
      Sort sort = Sort.by(Sort.Direction.DESC, by);
      return covidRepository.findTop5By(sort);

    } catch (Exception e) {
      throw new BadRequestException("Invalid attribute: " + by);
    }
  }

  @Override
  public Integer totalBy(String by) {
      return switch (by.toLowerCase()) {
          case "active" -> covidRepository.findAll().stream().mapToInt(Covid::getActive).sum();
          case "death" -> covidRepository.findAll().stream().mapToInt(Covid::getDeath).sum();
          case "recovered" -> covidRepository.findAll().stream().mapToInt(Covid::getRecovered).sum();
          default -> throw new BadRequestException("Invalid attribute: " + by);
      };
  }

  @Override
  public List<Report> getReport() {
    List<Covid> covidList = getAllCovid();

    return covidList.stream()
            .filter(covid -> covid.getContinent() != null && !covid.getContinent().isEmpty())
            .collect(groupingBy(Covid::getContinent))
            .entrySet().stream()
            .map(entry -> {
                return new Report(entry.getKey(), computeImpactFactor(
                          entry.getValue().stream().mapToInt(Covid::getDeath).sum(),
                          entry.getValue().stream().mapToInt(Covid::getActive).sum(),
                          entry.getValue().stream().mapToInt(Covid::getRecovered).sum())
                        );
            })
            .toList();
  }

  private Double computeImpactFactor(int death, int active, int recovered) {
    return BigDecimal.valueOf(death / (active + death + recovered))
            .setScale(3, RoundingMode.HALF_UP)
            .doubleValue();
  }
}

