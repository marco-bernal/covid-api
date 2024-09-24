package com.hackerrank.api.repository;

import com.hackerrank.api.model.Covid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CovidRepository extends JpaRepository<Covid, Long> {

    /**
     * Finds the top 5 sorted by a given field.
     *
     * @param sort sorted by a given field.
     * @return List of Covid objects.
     */
    List<Covid> findTop5By(Sort sort);
}
