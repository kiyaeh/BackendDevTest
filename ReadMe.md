# 🏦 Spring Boot REST API with CockroachDB

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![CockroachDB](https://img.shields.io/badge/CockroachDB-Latest-blue)
![License](https://img.shields.io/badge/license-MIT-green)

## 📑 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Testing](#testing)
- [Docker Deployment](#docker-deployment)
- [Contributing](#contributing)

## 🚀 Overview

A robust REST API built with Spring Boot and CockroachDB for managing user data and encrypted bank information. This project implements secure CRUD operations, data encryption, and comprehensive testing.

### Key Highlights
- 🔐 Secure bank data encryption
- 📑 Pagination support
- 🔄 CRUD operations
- 📝 Swagger documentation
- 🐳 Docker support
- ✅ Comprehensive testing

## ✨ Features

### Core Features
- User management with CRUD operations
- Bank information management
- AES encryption for sensitive data
- Pagination and sorting
- Swagger API documentation
- Docker containerization

### Enhanced Security
- AES-256 encryption for bank details
- Environment-based configuration
- Secure password handling

## 🛠 Tech Stack

- **Backend**: Spring Boot 2.7.0
- **Database**: CockroachDB
- **Security**: AES Encryption
- **Documentation**: Swagger/OpenAPI
- **Testing**: JUnit 5, Mockito
- **Build Tool**: Maven
- **Containerization**: Docker

## 📋 Prerequisites

- Java 17+
- Maven 3.8+
- Docker 
- CockroachDB
- Postman 

## 💻 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/spring-cockroachdb-api.git
cd spring-cockroachdb-api
2. Configure Environment
Create .env file:
DB_URL=jdbc:postgresql://<cockroachdb-url>:26257/<db-name>
DB_USERNAME=<username>
DB_PASSWORD=<password>
ENCRYPTION_SECRET_KEY=your-32-char-secret-key
ENCRYPTION_SALT=your-16-char-salt

3. Database Setup
Using CockroachDB Cloud
Create account at CockroachDB Cloud
Create new cluster
Get connection string
Local Setup
CREATE DATABASE test;

4. Build & Run
# Build
mvn clean install
# Run
mvn spring-boot:run
# Docker Build
docker build -t spring-cockroachdb-api .
docker run -p 8080:8080 spring-cockroachdb-api


📚 API Documentation
User Endpoints
Method	Endpoint	Description
POST	/api/users	Create user
GET	/api/users/{id}	Get user
GET	/api/users?page=0&size=10	Get users (paginated)
PUT	/api/users/{id}	Update user
DELETE	/api/users/{id}	Delete user
Bank Information Endpoints
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
    "accountType": "CHECKING",
    "bankAccountNumber": "1234567890"
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



🐳 Docker Deployment
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

Build and run:

docker build -t spring-cockroachdb-api .
docker run -p 8080:8080 spring-cockroachdb-api

🧪 Testing
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Generate test coverage report
mvn verify

📈 Performance
Pagination Example
GET /api/users?page=0&size=10&sort=firstName,desc
{
    "content": [...],
    "pageNumber": 0,
    "pageSize": 10,
    "totalElements": 100,
    "totalPages": 10,
    "last": false
}
🤝 Contributing
Fork the repository
Create feature branch (git checkout -b feature/AmazingFeature)
Commit changes (git commit -m 'Add AmazingFeature')
Push to branch (git push origin feature/AmazingFeature)
Open Pull Request
