# Quiz App 
### on Microservices architecture
Built with Spring boot and PostgreSQL

## Introduction
### Introduction to the Project:
1. This Quiz App is a backend-only project focused on providing robust functionality for quiz creation and scoring.
2. Employing microservices architecture, our backend ensures scalability, Maintainability, and Faster deployment.
### Key Features:
1. Microservices Architecture: Decoupled services for Quiz and Question management.
2. Seamless Integration: Smooth communication between services for a good user experience.
3. Data Storage: Utilizing PostgreSQL for data management.
### Objective:
1. To develop a high-performance backend system capable of handling quiz creation and scoring logic efficiently.
2. To showcase the benefits of microservices architecture in building scalable and maintainable backend solutions.

## Architecture overview
![image](https://github.com/AmarMe/spring-Microservices-quiz-app/assets/123172989/17b19a2f-b366-4a8c-a192-df6bd9a87df6)

## Quiz service
### Purpose:
It facilitates the creation of quizzes, retrieval of quiz questions by ID, and submission of quiz answers for scoring by leveraging Question service.
### End points:
### Create Quiz:
1. Endpoint: localhost:port/quiz/create
2. Desc: Allows users to create a new quiz by providing category, number of question, title.
### Get quiz questions by quiz ID:
1. Endpoint: localhost:port/quiz/get/{id}
2. Desc: Retrieves the questions associated with a quiz ID
### Submit quiz answer and calculate score:
1. Endpoint: localhost:port/quiz/submit/{id}
2. Desc: Accepts user submitted answers, calculate score and return the result

## Question service
### Purpose:
The Question Service manages question-related operations within the application, offering functionalities such as adding, deleting, updating, and retrieving questions.
Additionally, it provides services for generating quizzes, fetching questions for a specific quiz ID, and calculating scores.
### End points:
### CRUD endpoints:
1. To Create new questions, Read all the questions, Update a question by ID, Delete a question by ID
### Get questions by category :
1. Endpoint: localhost:port/question/category/{category}
2. Desc: It fetches all questions under a given category. Ex: Java
### Services provided to quiz service:
### End points:
1. Localhost:port/question/generate -  to generate a new quiz
2. Localhost:port/question/getQuestions – to fetch all the questions for list of quiz IDs
3. Localhost:port/question/getScore – to calculate score for the submitted answer

## Technologies used
### Spring boot:
1. Desc: A powerful java framework to build API endpoints for two services.
2. Benefits: Simplifies development configuration, enables rapid development and deployment of microservices.
### Spring cloud- Netflix Eureka server & client:
1. Desc: A service registry to register clients (API gateway and services) for microservices communication.
2. Benefits: Enables microservices to locate and communicate with other services registered in the server.
### Spring API Gateway:
1. Desc: A centralized entry point for users that manages and routes user requests to microservices.
2. Benefits: Provides load balancing, service discovery and simplifies client interactions by presenting a unified API.
### PostgreSQL:
1. Desc: An open-source relational database management system used for data storage management.
2. Benefits: High data reliability, Integrates seamlessly with Spring Boot for efficient data access.

## Deployment
1. Download the repo and open the repo in an IDE
2. Open postgreSQL(pgAdmin) and create databases for the services.(check the application.properties in each service)
3. First run the service-registry project
4. Run all other projects
5. Open browser and go to service-registry's running URL.(ex: localhost:portnumber). There you can see the services registered.
6. Open postman and run all the end points.








