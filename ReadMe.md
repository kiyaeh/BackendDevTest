# 🏦 Backend Developer Code Test: Spring Boot REST API with CockroachDB

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![CockroachDB](https://img.shields.io/badge/CockroachDB-Latest-blue)
![License](https://img.shields.io/badge/license-MIT-green)

## 📑 Table of Contents
- [Project Objective](#project-objective)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Testing](#testing)
- [Docker Deployment](#docker-deployment)

## 🎯 Project Objective

This code test demonstrates the implementation of a Spring Boot REST API with CockroachDB, featuring:
- Basic CRUD operations for user management
- Secure storage of encrypted bank information
- Integration with CockroachDB
- Comprehensive unit testing
- API documentation with Swagger

### Key Highlights
- 🔐 AES encryption for bank data
- 📑 Swagger API documentation
- 🔄 Complete CRUD operations
- 📊 Pagination support
- 🐳 Docker containerization
- ✅ Unit testing coverage

## ✨ Features

### Core Functionality
- User management with CRUD operations
- Encrypted bank information storage
- Foreign key relationships
- Data validation
- Environmental configuration
- Comprehensive error handling

### Enhanced Features
- AES-256 encryption for bank account numbers
- Pagination for data retrieval
- Swagger documentation
- Docker support
- Comprehensive unit tests

## 🛠 Tech Stack

- **Framework**: Spring Boot 2.7.0
- **Database**: CockroachDB
- **Security**: AES Encryption
- **Documentation**: Swagger/OpenAPI
- **Testing**: JUnit 5, Mockito
- **Build Tool**: Maven
- **Containerization**: Docker

## 📋 Prerequisites

- Java 17+
- Maven 3.8+
- CockroachDB
- Docker (optional)
- Postman (optional)

## 💻 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/kiyaeh/BackendDevTest.git
cd <your-project-directory>

2. Environment Configuration
Create .env file:
DB_URL=jdbc:postgresql://<cockroachdb-url>:26257/<database-name>?sslmode=verify-full
DB_USERNAME=<your-db-username>
DB_PASSWORD=<your-db-password>
ENCRYPTION_SECRET_KEY=12345678901234567890123456789012
ENCRYPTION_SALT=1234567890123456

3. Database Setup
CockroachDB Cloud Setup
Create an account at CockroachDB Cloud
Set up a new cluster
Create database:
CREATE DATABASE test;

Local Setup
Install CockroachDB
Start local cluster
Create database

4. Build & Run
# Build project
mvn clean install

# Run application
mvn spring-boot:run

# Docker Build 
docker build -t backend-code-test .
docker run -p 8080:8080 backend-code-test

📚 API Documentation
Entity Structure
User Entity
{
    "id": "UUID",
    "firstName": "String",
    "lastName": "String",
    "email": "String",
    "phoneNumber": "String"
}
BankInformation Entity

{
    "id": "UUID",
    "userId": "UUID",
    "bankAccountNumber": "String (encrypted)",
    "bankName": "String",
    "accountType": "String"
}
API Endpoints

User Management

Method	Endpoint	Description
POST	/api/users	Create user
GET	/api/users/{id}	Get user
PUT	/api/users/{id}	Update user
DELETE	/api/users/{id}	Delete user

Bank Information Management

Method	Endpoint	Description

POST	/api/users/{userId}/bank-information	Add bank info
GET	/api/users/{userId}/bank-information	Get bank info
PUT	/api/users/{userId}/bank-information	Update bank info
DELETE	/api/users/{userId}/bank-information	Delete bank info
Sample Requests
Create User

POST /api/users
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "+1234567890"
}
Add Bank Information

POST /api/users/{userId}/bank-information
{
    "bankName": "Bank of America",
    "accountType": "Checking",
    "bankAccountNumber": "123456789"
}
🔒 Security
Encryption Configuration

@Configuration
public class SecurityConfig {
    @Value("${encryption.secret-key}")
    private String secretKey;

    @Value("${encryption.salt}")
    private String salt;

    @Bean
    public EncryptionUtil encryptionUtil() {
        return new EncryptionUtil(secretKey, salt);
    }
}
Application Properties.
yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

encryption:
  secret-key: ${ENCRYPTION_SECRET_KEY}
  salt: ${ENCRYPTION_SALT}
🧪 Testing
Running Tests

# Run all tests
mvn test

# Run specific test
mvn test -Dtest=UserServiceTest

# Generate coverage report
mvn verify
Test Coverage
Service Layer Tests
Controller Layer Tests
Encryption/Decryption Tests
Integration Tests
🐳 Docker Deployment
Dockerfile
dockerfile

FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
Build & Run

docker build -t backend-code-test .
docker run -p 8080:8080 backend-code-test
📈 Additional Features
Pagination Example

GET /api/users?page=0&size=10&sort=firstName,desc
Response:

{
    "content": [...],
    "pageNumber": 0,
    "pageSize": 10,
    "totalElements": 100,
    "totalPages": 10,
    "last": false
}
Swagger UI
Access API documentation at:
http://localhost:8080/swagger-ui.html
