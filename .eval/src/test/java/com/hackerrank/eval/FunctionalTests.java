package com.hackerrank.eval;

import com.hackerrank.eval.extensions.RESTExtension;
import com.hackerrank.eval.model.Covid;
import com.hackerrank.eval.model.Report;
import static io.restassured.RestAssured.get;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith({RESTExtension.class})
class FunctionalTests {

  private static Map<String, Report> getExpectedReport() {
    Map<String, Report> map = new HashMap<>();
    map.put("asia", Report.builder()
            .continent("asia")
            .impactFactor(0.04716)
            .build());

    map.put("xx", Report.builder()
            .continent("xx")
            .impactFactor(0.1)
            .build());

    map.put("eu", Report.builder()
            .continent("eu")
            .impactFactor(0.0075)
            .build());
    return map;
  }

  @Test
  @DisplayName("test top 5")
  void testTop5() {
    List<Covid> actual = Arrays.asList(get("/covid/top5?by=death")
            .then()
            .statusCode(SC_OK)
            .extract()
            .response()
            .as(Covid[].class));

    assertThat(
            actual.stream().map(Covid::getCountry).toArray(),
            arrayContaining(Arrays.asList("country4", "country2", "country5", "country7", "country3").toArray()));
  }

  @Test
  @DisplayName("total")
  void testTotal() {
    Integer actual = get("/covid/total?by=death")
            .then()
            .statusCode(SC_OK)
            .extract()
            .response()
            .as(Integer.class);

    Assertions.assertEquals(28, actual);
  }

  @Test
  void correctDashboardReportReturned() {
    Report[] actual =
            get("/covid/report/covidDashboard")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response()
                    .as(Report[].class);
    Map<String, Report> actualMap = new HashMap<>();
    for (int i = 0; i < actual.length; i++) {
      actualMap.put(actual[i].getContinent(), actual[i]);
    }

    Map<String, Report> expectedMap = getExpectedReport();

    Assertions.assertEquals(expectedMap.size(), actualMap.size());

    for (Map.Entry<String, Report> e : expectedMap.entrySet()) {
      Assertions.assertTrue(actualMap.containsKey(e.getKey()));
      Assertions.assertTrue(assertEquals(e.getValue().getImpactFactor(), actualMap.get(e.getKey()).getImpactFactor()));
    }
  }

  public boolean assertEquals(double x, double y) {
    x = (long) (x * Math.pow(10, 2));
    y = (long) (y * Math.pow(10, 2));
    return x == y;
  }
}