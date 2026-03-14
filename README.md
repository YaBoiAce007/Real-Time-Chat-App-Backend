# Real-Time Chat Application Backend

This backend powers a real-time chat application. It handles user registration and authentication, chat room creation, message persistence, and real-time communication between clients using WebSockets.

The backend exposes REST APIs for authentication and chat room management while WebSockets are used for real-time message exchange.

---

# Features

- User authentication using **JWT**
- Token validation and expiration handling
- Chat room creation and management
- Real-time messaging using **WebSockets (STOMP)**
- Message persistence using a relational database
- Pagination support for retrieving older messages
- Duplicate username and chat room name management
- CORS configuration
- WebSocket **inbound channel interceptor** for handling `CONNECT` frames

---

# Technologies Used

Backend Framework
- Spring Boot
- Spring Web
- Spring Security

Data Layer
- Spring Data JPA
- PostgreSQL

Authentication
- JSON Web Tokens (JWT)

Real-Time Communication
- WebSockets
- STOMP Protocol

Build Tool
- Maven

---

# API Endpoints

Base URL:

```
/real-time-chat-app
```

Authentication

```
POST /register
POST /login
GET  /validate
```

Chat Rooms

```
GET  /rooms
POST /rooms
```

Messages

```
GET /rooms/{roomId}/messages
```

---

# Deployment

The backend is deployed on **Railway**.

---

# Running the Backend Locally

Follow the steps below to run the backend on your local machine.

## 1. Prerequisites

Ensure the following tools are installed:

- Java (JDK 17 or later recommended)
- Maven
- PostgreSQL

---

## 2. Clone the Repository

Open a terminal and run:

```bash
git clone https://github.com/YaBoiAce007/Real-Time-Chat-App-Backend.git
```

---

## 3. Navigate to the Project Directory

```
cd Real-Time-Chat-App-Backend
```

---

## 4. Configure Environment Variables

The application requires a **JWT secret key** for signing authentication tokens.

Generate a secure secret key using the following Java program.

### Secret Key Generator

```java
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class g {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGenerator.generateKey();
        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
    }
}
```

Compile and run the program:

```
javac g.java
java g
```

The program will output a **Base64 encoded secret key**.

Example output:

```
wZ0Jm7kV3x9C4F9gS7N3U1p6F2q8K9dT3A6b4L2V5M0=
```

Set this value as an environment variable:

```
JWT_SECRET=<generated_secret>
```

The backend reads the secret using:

```
jwt.secret=${JWT_SECRET}
```

---

## 5. Configure Database

Create a PostgreSQL database and update your database configuration in:

```
application.properties
```

Example configuration:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/chatdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## 6. Build the Project

Since this is a Maven project, build it using:

```
mvn clean install
```

---

## 7. Run the Application

Start the Spring Boot application using:

```
mvn spring-boot:run
```

The backend server will start locally.

---

# Author

Aniketh Gurung