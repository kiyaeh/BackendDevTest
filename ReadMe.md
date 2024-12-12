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




