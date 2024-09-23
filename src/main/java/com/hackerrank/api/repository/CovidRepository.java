package com.hackerrank.api.repository;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CovidRepository extends JpaRepository<Covid, Long> {

    /**
     * Doesn't work with dynamic order by field.
     */
//    @Query(value = "SELECT * FROM COVID ORDER BY DEATH DESC LIMIT 5", nativeQuery = true)
//    List<Covid> findTop5ByNativeQuery(Sort sort);

    List<Covid> findTop5By(Sort sort);

    /**
     * Native SQL, no dynamic parameter: Works.
     */
//    @Query(value = "SELECT SUM(ACTIVE) FROM Covid", nativeQuery = true)
//    Integer getTotalByActive();

    /**
     * JPQL, no dynamic parameter: Works. Research how to pass a parameter to the sum function.
     */
//    @Query(value = "SELECT SUM(c.active) FROM Covid c")
//    Integer getTotalByActiveJpql();

    /**
     * Passing the parameter doesn't work. Complains about the data type being a String.
     */
//    @Query(value = "SELECT SUM(:by) FROM Covid", nativeQuery = true)
//    Integer getTotalBy(@Param("by") String by);
}
