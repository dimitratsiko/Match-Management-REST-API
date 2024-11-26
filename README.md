
# Match Management REST API

This project implements a RESTful web service for managing sports matches and their associated odds. Built using Java, Spring Boot, and PostgreSQL, it provides a robust and extensible framework for CRUD operations and search functionalities.

---

## Features

1. **Abstract Match Entities**
    - Created an abstract `Match` class extended by `FootballMatch` and `BasketballMatch`.
    - Extended business logic allows for distinct handling of each sport type without requiring refactoring.
    - Additional fields:
        - `FootballNotes` for football-specific details.
        - `BasketballNotes` for basketball-specific details.

2. **Database Management with Liquibase**
    - Integrated Liquibase for version-controlled database schema management.
    - Changeset includes mock data provided in `insert.sql` for initial testing and demonstration.

3. **API with Flexible Search**
    - Implemented APIs using Spring Data JPA Specifications for advanced and flexible search criteria.

4. **Exception Handling**
    - Added `ControllerAdvice` to manage custom exceptions like `MatchNotFoundException`.

5. **Multi-Database Support**
    - Configured a primary datasource using the @Primary annotation, enabling it as the default data source while supporting seamless integration with additional secondary data sources if needed.

6. **AOP for Logging**
    - Implemented Aspect-Oriented Programming (AOP) with a custom @LogExecutionTime annotation to measure and log method execution times, enhancing performance monitoring and debugging.

7. **Swagger UI for API Testing**
    - Added Swagger UI to the project for interactive API documentation and testing. This provides a convenient interface to explore and test the available endpoints directly from the browser.

---

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for developing REST APIs.
- **PostgreSQL**: Relational database.
- **Liquibase**: Database migration and version control.
- **Maven**: Dependency and build management.

---

## Setup Instructions

1. **Prerequisites**
    - Java 17 or higher
    - PostgreSQL
    - Maven

2. **Database Configuration**
    - Update `application.yml` with your PostgreSQL connection details.

3. **Run the Application**

4. **Accessing the APIs**
    - Base URL: `http://localhost:8080`
    - Example Endpoints:
        - `PUT api/match/update`: Update the match and its odds.
        - `POST api/match/save`: Add a new match.
        - `GET api/match/{matchId}`: Retrieve a single match and its odd via its id.
        - `GET api/match`: Search for a match.
        - `DELETE api/match/{matchId}`: Delete a match.
        - `PUT api/match-odd/update`: Update the match odd.
        - `POST api/match-odd/save`: Add a new match odd.
        - `GET api/match-odd/{matchId}`: Retrieve a single match odd.
        - `GET api/match-odd`: Search for a match odd.
        - `DELETE api/match-odd/{matchId}`: Delete a match odd.
    - Swagger documentation and endpoints' access: `http://localhost:8080/swagger-ui/index.html`

---

## Future Enhancements

- Implement Docker for containerized deployment.
- Add unit and integration tests for all components.

