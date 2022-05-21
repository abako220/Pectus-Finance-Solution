# Coding challenge Backend Exercise - Python

Hello! If you are going through this document, we're likely chatting with you about a technical role at Pectus Finance. If so, congratulations 🎉 !

```
TIMEBOX:    2-3 hours max. We mean it! Set at timer and hard-stop at 3 hours ⏱
LANGUAGES:  Python
FRAMEWORKS: Flask, FastApi, Serverless Frameworks, AWS Lambda Functions, API Gateway
TESTS:      nice to have, but not mandatory
DOCS:       nice to have, but not mandatory
```

## Overview

This exercise is to implement the best possible solution as per your knowledge in the time alloted. We're evaluating your ability to take a set of requirements and spike a holistic solution that demonstrates craftsmanship, thoughtfulness and good architectural design. This is **NOT** a test of how well you know Python/Flask/FastApi, nor should you try to impress us with overly clever and obtuse solutions. If you want to impress us, build something that is **beautiful**, **intuitive** and easy to **debug/test/extend** :smiley: .

**Notes:** Ideally your solution should have some way to run locally and test the results so we can fully analyze your efforts.

---

> Please use expanses.csv datasets.

### Exercise A: Expose an API for querying expanses data

The goal of this exercise is to design a read-only API (REST) that returns one or more records from static set of expanses (Please use expanses.csv datasets) data.

#### User Story: As a developer I want to

- list expanses data via API `GET` request
  - Filter by one or more fields/attributes (e.g. `/expanses_data?amount[gte]=1400&member_name=Sam` )
  - Sort by one or more fields/attributes (e.g. `/expanses_data?sort=departments&order=desc`)
- fetch a single record via GET request
  - return a sparse fieldset (e.g. `/expanses_data?fields=departments,member_name,amount`)
- get total sum of expanses by departments, project_name, date and by member_name
  (e.g. `/aggregates?by=departments` or `/aggregates?by=project_name` or `/aggregates?by=member_name` ..etc)

### A few quick notes on submitting Exercise A

- Don't worry about any web application concerns other than serializing JSON and returning via a GET request.
- The example above (`/expanses_data`...) is not a contract. Feel free to design the URL structure and JSON schema that you believe creates the best client/consumer experience

---

## Submitting your exercise

- Please sent your task solutions github repository link at nishant@pectusfinance.com.
- If you have any questions related to this coding challenge please write to Nishant /He/Him (nishant@pectusfinance.com)
