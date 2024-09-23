package com.hackerrank.api.service;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import java.util.List;

// TODO: Delete me
public interface CovidService {

  List<Covid> getAllCovid();

  Covid createNewCovid(Covid covid);

  Covid getCovidById(Long id);

  List<Covid> top5By(String by);

  Integer totalBy(String by);

  List<Report> getReport();
}
