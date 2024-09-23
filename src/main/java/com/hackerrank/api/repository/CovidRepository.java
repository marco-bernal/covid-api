package com.hackerrank.api.repository;

import com.hackerrank.api.model.Covid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CovidRepository extends JpaRepository<Covid, Long> {

    List<Covid> findTop5By(Sort sort);
}
