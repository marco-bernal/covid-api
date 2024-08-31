## Environment
- Java version: 17
- Maven version: 3.*
- Spring Boot version: 3.2.3

## Data
Example of a Covid data JSON object:
```json
{
    "id":1,
    "country":"MyCountry",
    "continent" : "antarctica",
    "active":574,
    "death":45,
    "recovered": 7000
}
```

## Requirements
In this project, data related to covid are provided for many countries. Note that all the data are virtual.

You have to implement `/covid` REST endpoint for following 4 operations.

`GET` request to `/covid/byId/{id}`:
* return the covid entry with given id and status code 200
* if the requested covid entry doesn't exist, then status code 404 should be returned

`GET` request to `/covid/top5?by={by}`:
* return the top 5 covid entries sorted by given field and status code 200.
* for example: `/covid/top5?by=death` gives total deaths
* if give `by` is invalid attribute, return status code 400

`GET` request to `/covid/total?by={by}`:
* return the total value summed by given field and status code 200
* for example: `/covid/total?by=active` gives total active cases
* if give `by` is invalid attribute, return status code 400
 
`GET` request to `/scan/report/scanDashboard`:
* it needs to generate report like below JSON where it needs to group by `continent` and find the impact factor of each group.
* status code should be 200 and precision needs to be till 3 decimal places.

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

* exclude the entries from calculation which have `continent` null or empty.
* impactFactor formula is:
```
impactFactor(europe) = death/sum(active+death+recovered)
```

## Commands
- run: 
```bash
mvn clean spring-boot:run
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```