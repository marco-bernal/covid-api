package com.hackerrank.api.repository;

import com.hackerrank.api.model.Covid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidRepository extends JpaRepository<Covid, Long> {
}
