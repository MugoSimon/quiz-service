# Quiz Service

The Quiz Service is a microservice responsible for managing the quiz-related functionality in the application.

## Features
- Handles the submission of quiz responses
- Calculates the score based on the user's answers
- Provides endpoints to retrieve and update quiz-related data

## Configuration
The Quiz Service requires the following configuration:

```properties
# Database Config
spring.datasource.url=jdbc:mysql://localhost:3306/quizService?enabledTLSProtocols=TLSv1.2
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
Make sure to update the database connection details according to your environment.

Deployment
The Quiz Service can be deployed as a standalone Spring Boot application. Ensure that the Eureka server is running and the service is properly registered with it.

Endpoints
The Quiz Service exposes the following endpoints:

POST /quiz/submit: Submits the user's quiz responses and calculates the score.
GET /quiz/{id}: Retrieves the details of a specific quiz.
Dependencies
Spring Boot
Spring Data JPA
MySQL
Eureka (for service discovery)
Known Issues
The Quiz Service does not have any authentication or authorization mechanisms in place.
The error handling and logging could be improved.
Future Improvements
Implement authentication and authorization for the Quiz Service.
Improve the error handling and logging mechanisms.
Add more test cases to ensure the reliability of the service.