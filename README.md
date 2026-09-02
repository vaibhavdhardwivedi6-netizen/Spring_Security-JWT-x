# 🔐 Spring Security & JWT Authentication Service

[![Spring Boot](https://img.shields.io/badge/Spring--Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring--Security-6.x-blue.svg)](https://spring.io/projects/spring-security)
[![JWT](https://img.shields.io/badge/JWT-Authentication-orange.svg)](https://jwt.io/)
[![Eureka Client](https://img.shields.io/badge/Eureka-Client-yellow.svg)](https://spring.io/projects/spring-cloud-netflix)

Centralized **Authentication & Authorization Microservice** for the **Hospital Management System (HMS)** ecosystem. Built using Spring Boot, Spring Security 6, and JSON Web Tokens (JWT), this service handles user registration, credentials validation, role management, and secure token generation.

---

## 🛠️ Architecture & Tech Stack

- **Java Version**: 17+
- **Framework**: Spring Boot 3.x
- **Security**: Spring Security & JWT (`jjwt`)
- **Database**: MySQL with Spring Data JPA & Hibernate
- **Service Discovery**: Netflix Eureka Client
- **Utilities**: Lombok, Maven

---

## ⚙️ Configuration & Environment Variables

This microservice connects to MySQL and registers with the Eureka Service Discovery server (`http://localhost:8761/eureka/`).

### Required Environment Variables

Before launching the service, set the following environment variables:

| Variable | Description | Example |
|---|---|---|
| `URL` | JDBC Connection URL for MySQL database | `jdbc:mysql://localhost:3306/hms_auth_db?createDatabaseIfNotExist=true` |
| `USERNAME` | Database username | `root` |
| `PASSWORD` | Database password | `rootpassword` |
| `SCKEY` | Secret Key for signing JWT tokens | `YourSuperSecretKeyForJWTTokenSigning2026!` |

### Server Port
- **Port**: `8084`

---

## 🚀 REST API Reference

### Base Path: `/api/auth`

#### 1. Register User
- **Method**: `POST`
- **Path**: `/api/auth/register`
- **Request Body**:
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "securepassword123",
  "roles": ["ROLE_ADMIN"]
}
```
- **Response**: `200 OK` (User registered successfully)

---

#### 2. Authenticate / Login
- **Method**: `POST`
- **Path**: `/api/auth/login`
- **Request Body**:
```json
{
  "username": "john_doe",
  "password": "securepassword123"
}
```
- **Response**: `200 OK`
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
```

---

## 🚦 Getting Started

### 1. Prerequisites
- Java 17 or higher
- Maven 3.8+
- MySQL Server running
- Eureka Server (`Hospital-Server`) running on `localhost:8761`

### 2. Run Locally

```bash
# Clone the repository
git clone https://github.com/vaibhavdhardwivedi6-netizen/Spring_Security-JWT-x.git
cd Spring_Security-JWT-x

# Set environment variables (PowerShell example)
$env:URL="jdbc:mysql://localhost:3306/hms_auth"
$env:USERNAME="root"
$env:PASSWORD="root"
$env:SCKEY="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"

# Build and run using Maven wrapper
./mvnw spring-boot:run
```

---

## 📡 Service Discovery Integration

Upon startup, `Spring_Security-JWT` registers itself automatically with the Netflix Eureka Discovery Server under the name `SPRING_SECURITY-JWT`.

- **Eureka Server Dashboard**: [http://localhost:8761](http://localhost:8761)