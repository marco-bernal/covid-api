package com.hackerrank.api.service.impl;

import com.hackerrank.api.exception.BadRequestException;
import com.hackerrank.api.exception.ElementNotFoundException;
import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import com.hackerrank.api.repository.CovidRepository;
import com.hackerrank.api.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultCovidService implements CovidService {
  private final CovidRepository covidRepository;

  @Autowired
  DefaultCovidService(CovidRepository covidRepository) {
    this.covidRepository = covidRepository;
  }

  @Override
  public List<Covid> getAllCovid() {
    return covidRepository.findAll();
  }


  @Override
  public Covid createNewCovid(Covid covid) {
    if (covid.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Scan");
    }

    return covidRepository.save(covid);
  }

  @Override
  public Covid getCovidById(Long id) {
    return covidRepository.findById(id).
            orElseThrow(() -> new ElementNotFoundException("Covid by Id not found:" + id));
  }

  @Override
  public List<Covid> top5By(String by) {
    //return covidRepository.findAll(Example.of())
    return null;
  }

  @Override
  public Integer totalBy(String by) {
    return null;
  }

  @Override
  public List<Report> getReport() {
    return null;
  }
}
