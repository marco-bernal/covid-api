## Covid API
Service that gathers and presents illustrative covid related info. 

## Environment
- Java version: 23
- Spring Boot version: 3.4.3

## Data
Example of a CovidDto JSON object:
```json
{
  "country" : "Germany",
  "continent" : "Europe",
  "confirmed" : 7935,
  "death" : 45,
  "recovered" : 7000,  
  "active" : 574
}
```

## Endpoints

`POST` request to `/covid/`:
* RequestBody `CovidDto` object.
* Returns a `CovidDto` object and status code `201`.

`GET` request to `/covid/byId/{id}`:
* Returns a `CovidDto` object and status code `200`.
* If the `id` doesn't exist, returns status code `404`.

`GET` request to `/covid/top5?by={by}`:
* Returns the top 5 entries, sorted by a given field `by` and status code `200`.
* e.g.: `/covid/top5?by=death` retrieves top 5 by death.
* If the attribute `by` is invalid, returns status code `400`.

`GET` request to `/covid/total?by={by}`:
* Returns the total sum by the given field `by` and status code `200`.
* e.g.: `/covid/total?by=active` retrieves the total active cases.
* If the attribute `by` is invalid, returns status code `400`.
 
`GET` request to `/scan/report/scanDashboard`:
* Generates a report similar to the JSON below. Where it's grouped by `continent` and computes the `impact factor`.
* Returns a status code `200`. 
* Excludes entries with `continent` null or empty.
* Impact Factor formula: ``` impactFactor = death/sum(active+death+recovered) ``` Decimal precision of 3 digits.


 ```json
[
{
    "continent":"antarctica",
    "impactFactor": 0.3
    
},
{
    "continent":"africa",
    "impactFactor": 0.564
},
{
    "continent":"europe",
    "impactFactor": 0.234
}
]
```

## ToDo
Tests
* Create meta annotation for IT.
* Add test containers support for DB IT.
* Create IT for Repo, Service and Controller Layers.
* Set up / configure Jacoco for code coverage (100%).

* Document the API (swagger or open API).

DevOps
* Add dockerfile and docker-compose for service and db.
* Add GitHub actions.

SRE
* Add proper health monitoring: Prometheus and Grafana in a compose file.

Security
* TBD