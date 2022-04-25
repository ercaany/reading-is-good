# reading-is-good

ReadingIsGood is an online books retail firm which operates only on the Internet

# TechStack

* Java 17
* Spring Boot 2.6.7
* Mongo DB
* Lombok
* SpringDoc Open Api
* Docker
* Maven

# How To Use?
MongoDB And Spring Boot app are dockerized therefore we have to run these commands in order to run our application.

`mvn -DskipTests=true  package`

`docker-compose build`

`docker-compose up -d`

Spring Boot app will be running on port 8080 and MongoDB will be up on port 27017

Swagger documentation will be available at [Swagger](http://localhost:8080/swagger-ui/index.html)


# Available Endpoints

* /auth
* /orders
* /books
* /customers
* /statistics

Details are available on Swagger!

# Security
To secure endpoints **Jwt & Spring Security** are used.

# Authentication

In order to use APIs you have to get a bearer token from /auth endpoint.
Any username-password double is eligible for authentication.

It is assumed that _**username and password are the same**_. Any other call to /auth endpoint will fail to get a token.
Generated token should be added as a Bearer Token in Postman.

# Consistency of Stock

Consistency of the book stock, which is the key point of this case study, is ensured by
Optimistic Lock mechanism of Spring Data annotation @Version. @Version annoted column is
only used for optimistic locking mechanism and nothing else.

# Tests

Both Unit and Integration tests are provided for book, order & customer controllers.

# Postman

Postman collection is provided under the postman directory.
