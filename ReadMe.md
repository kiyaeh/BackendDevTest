Backend Developer Code Test: Spring Boot REST API with CockroachDB
Project Overview
This project demonstrates a REST API built with Spring Boot and CockroachDB to manage user data and encrypted bank information.
The API implements CRUD operations for users and their associated bank details, ensuring secure encryption of sensitive data.
Unit tests are also included to ensure the reliability of the service and controller layers.

Features
User and Bank Information management with CRUD operations.
CockroachDB integration for data persistence.
Encryption and decryption of sensitive bank account numbers using AES.
Unit tests for services and controllers with JUnit and Mockito.
Environment variable support for sensitive configurations (e.g., database credentials, encryption keys).

Prerequisites
Java 17 or higher
Maven 3.8+
CockroachDB (Cloud or Local setup)
Postman (optional, for API testing)

Installation Guide
1. Clone the Repository
   git clone <repository_url>
   cd <repository_directory>
2. Configure Environment Variables
   Create a .env file in the root directory with the following contents:

DB_URL=jdbc:postgresql://<your-cockroachdb-url>:26257/<database-name>?sslmode=verify-full
DB_USERNAME=<your-db-username>
DB_PASSWORD=<your-db-password>
ENCRYPTION_SECRET_KEY=12345678901234567890123456789012
ENCRYPTION_SALT=1234567890123456


3. Set Up CockroachDB
   If using CockroachDB Cloud:
   Create a database and obtain the connection URL, username, and password.
   If running locally:
   Download and start CockroachDB locally.
   Create a database using SQL:
   sql
   CREATE DATABASE test;

4. Build and Run the Project
   Run the following commands in the project directory:
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

5. Access the Application
   The API is accessible at: http://localhost:8080/api
   API Endpoints

   User Endpoints

   HTTP Method	Endpoint	Description
   POST	/users	Create a new user
   GET	/users/{id}	Retrieve user by ID
   PUT	/users/{id}	Update user details
   DELETE	/users/{id}	Delete user

   Bank Information Endpoints
   HTTP Method	Endpoint	Description
   POST	/users/{userId}/bank-information	Add bank information for a user
   GET	/users/{userId}/bank-information	Retrieve user's bank information
   PUT	/users/{userId}/bank-information	Update user's bank information
   DELETE	/users/{userId}/bank-information	Delete user's bank information



Here’s a detailed and professional documentation draft for your Spring Boot project, incorporating the provided information:

Backend Developer Code Test: Spring Boot REST API with CockroachDB
Project Overview
This project demonstrates a REST API built with Spring Boot and CockroachDB to manage user data and encrypted bank information. The API implements CRUD operations for users and their associated bank details, ensuring secure encryption of sensitive data. Unit tests are also included to ensure the reliability of the service and controller layers.

Features
User and Bank Information management with CRUD operations.
CockroachDB integration for data persistence.
Encryption and decryption of sensitive bank account numbers using AES.
Unit tests for services and controllers with JUnit and Mockito.
Environment variable support for sensitive configurations (e.g., database credentials, encryption keys).
Prerequisites
Java 17 or higher
Maven 3.8+
CockroachDB (Cloud or Local setup)
Postman (optional, for API testing)
Installation Guide
1. Clone the Repository
   bash
   Copy code
   git clone <repository_url>
   cd <repository_directory>
2. Configure Environment Variables
   Create a .env file in the root directory with the following contents:

plaintext
Copy code
DB_URL=jdbc:postgresql://<your-cockroachdb-url>:26257/<database-name>?sslmode=verify-full
DB_USERNAME=<your-db-username>
DB_PASSWORD=<your-db-password>
ENCRYPTION_SECRET_KEY=12345678901234567890123456789012
ENCRYPTION_SALT=1234567890123456
3. Set Up CockroachDB
   If using CockroachDB Cloud:
   Create a database and obtain the connection URL, username, and password.
   If running locally:
   Download and start CockroachDB locally.
   Create a database using SQL:
   sql
   Copy code
   CREATE DATABASE test;
4. Build and Run the Project
   Run the following commands in the project directory:

bash
Copy code
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
5. Access the Application
   The API is accessible at: http://localhost:8080/api
   API Endpoints

   User Endpoints
   HTTP Method	Endpoint	Description
   POST	/users	Create a new user
   GET	/users/{id}	Retrieve user by ID
   PUT	/users/{id}	Update user details
   DELETE	/users/{id}	Delete user
   Bank Information Endpoints
   HTTP Method	Endpoint	Description
   POST	/users/{userId}/bank-information	Add bank information for a user
   GET	/users/{userId}/bank-information	Retrieve user's bank information
   PUT	/users/{userId}/bank-information	Update user's bank information
   DELETE	/users/{userId}/bank-information	Delete user's bank information



   Sample Request & Response
   POST /users
   Request
   {
   "firstName": "John",
   "lastName": "Doe",
   "email": "john.doe@example.com",
   "phoneNumber": "+1234567890"
   }
   Response
   {
   "id": "e7bb8d6e-7f72-4f49-b67d-6ecbf12be19a",
   "firstName": "John",
   "lastName": "Doe",
   "email": "john.doe@example.com",
   "phoneNumber": "+1234567890"
   }

   POST /users/{userId}/bank-information
   Content-Type: application/json

{
"bankName": "Bank of America",
"accountType": "Checking",
"bankAccountNumber": "123456789"
}

response
{
"id": "uuid",
"bankName": "Bank of America",
"accountType": "Checking",
"bankAccountNumber": "EncryptedValue"
}
   Configuration Details
   application.yml

   spring:
   datasource:
   url: "${DB_URL}"
   username: "${DB_USERNAME}"
   password: "${DB_PASSWORD}"
   driver-class-name: org.postgresql.Driver

jpa:
hibernate:
ddl-auto: update
show-sql: true
properties:
hibernate:
format_sql: true
dialect: org.hibernate.dialect.PostgreSQLDialect

server:
port: 8080
servlet:
context-path: /api

encryption:
secret-key: "${ENCRYPTION_SECRET_KEY}"
salt: "${ENCRYPTION_SALT}"


Encryption Details
The BankInformation entity uses AES encryption to secure sensitive data:

The bankAccountNumber field is encrypted before being stored in the database.
Decryption is handled transparently when retrieving data through the API.

Encryption Configuration
Code Reference:

@Configuration
public class SecurityConfig {
@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}
}

Unit Testing
Unit tests are implemented using JUnit and Mockito:

Service Layer: Tests business logic.
Controller Layer: Ensures proper routing and response validation.
Run tests with:
mvn test
