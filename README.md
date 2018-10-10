# JavaEnterprise_Forum

This is a forum application made in Java EE and Spring, no focus on frontend design.
This is not entirely finished due to lack of time.

**Project Structure**

The project contains 3 submodules
* backend
    * Entities
    * Services
    * ServiceTests
* frontend
    * Controllers
    * Application Runner
* reports
    * JaCoCo coverage
    * OWASP check

## How to run code

1. Clone this repo
2. Run from project root folder: 
<br/> ```mvn install``` will install and generate reports **with** running tests
<br/> ```mvn install -DskipTests``` will install and generate reports **without** running tests
3. Run **LocalApplicationRunner** located in ~/frontend/src/test/java/no/cmarker/frontend/LocalApplicationRunner.java
4. Open ```localhost:8080/``` in your browser

## Unfinished parts
- Login
- Signup
- No Selenium tests