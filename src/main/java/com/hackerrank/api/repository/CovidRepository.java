package com.hackerrank.api.repository;

import com.hackerrank.api.model.Covid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CovidRepository extends JpaRepository<Covid, Long> {


    //private String country;
    //  private String continent;
    //  private Integer active;
    //  private Integer death;
    //  private Integer recovered;
//    @Query(value = "SELECT * FROM Covid "
//            + "WHERE continent = :continent "
//            + "OR active = :active "
//            + "OR death = :death "
//            + "OR active = :active "
//            + "ORDER BY = :by LIMIT 5"
//            , nativeQuery = true)
//    List<Covid> getTop5ByField(
//            @Param("by") String by,
//            @Param("continent") String continent,
//            @Param("active") String active,
//            @Param("death") String death,
//            @Param("recovered") String recovered
//    );
//
//    @Query(value = "SELECT continent, "
//            , nativeQuery = true)
//    List<Covid> getReport();


}
