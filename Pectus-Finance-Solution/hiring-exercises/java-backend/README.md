#### Application Design

---
This application followed an Object-Oriented design pattern, taking advantage of the SOLID principles.
It is built with a TDD approach

##### How to Clean and Build

----
./gradlew clean build

##### How to Run

---
```javascript
./gradlew bootRun
```

##### API
1. Get Expanses data with query params as filter(s)
```javascript
GET http://localhost:8000/api/expanses_data?amount=1400&member_name=Sam&fields=departments,member_name,amount&sort=departments&order=desc
```
2. Get Aggregates by a particular field

```javascript
GET http://localhost:8000/api/expanses_data/aggregates?by=
```

#### H2 Database Console Log

``````Web
3. H2 Database Console

````http://localhost:8000/api/h2-console
````