# Communication Scheduling API

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-blue.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-supported-blue.svg)](https://www.docker.com)
[![Actuator](https://img.shields.io/badge/Actuator-enabled-orange.svg)](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)

This project is a REST API developed in Java with Spring Boot. It serves as a foundational communication platform, providing essential endpoints to schedule, retrieve, and cancel communications.

---

## Features

- **Schedule:** Create new communication notifications for a future date and time.
- **Cancel:** Cancel a previously scheduled notification.
- **Retrieve:** Look up a notification by its unique ID.
- **Observability:** Includes custom metrics via Spring Boot Actuator to monitor request timings.
- **API Documentation:** Interactive API documentation provided by Swagger UI.

---

## Tech Stack

- **Core:** Java 21, Spring Boot 3, Maven
- **Database:** PostgreSQL (primary), H2 (for tests)
- **API & Documentation:** Spring Web, SpringDoc OpenAPI (Swagger UI)
- **Testing:** JUnit 5, Mockito
- **Containerization:** Docker, Docker Compose
- **Observability:** Spring Boot Actuator, Micrometer, Prometheus, Grafana

---

## Prerequisites

- Java JDK 21
- Maven 3.9+
- Docker & Docker Compose
- Git

---

## How to Run

You can run this project using Docker Compose (recommended) or locally.

### 1. Running with Docker Compose (Recommended)

This is the simplest way to get the entire application stack, including the PostgreSQL database, up and running.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/SAID-ASAAD/communication-scheduling-api.git
    cd communication-scheduling-api
    ```

2.  **Build the application JAR:**
    This step compiles the code and creates the executable JAR file that Docker will use.
    ```bash
    mvn clean install
    ```

3.  **Start the services using Docker Compose:**
    This command will build the application's Docker image and start both the application and database containers.
    ```bash
    docker compose up --build
    ```

The API will be available at `http://localhost:8080`.

### 2. Running Locally

This method requires you to have a PostgreSQL instance running separately.

1.  **Clone and build:**
    ```bash
    git clone https://github.com/SAID-ASAAD/communication-scheduling-api.git
    cd communication-scheduling-api
    mvn clean install
    ```

2.  **Configure the database:**
    Make sure your PostgreSQL server is running. You may need to update the database connection details in `src/main/resources/application.properties`.

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

---

## API Documentation

### Interactive Documentation (Swagger UI)

For a full, interactive API documentation where you can see all endpoints and test them directly from your browser, navigate to:

**[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### Endpoints Overview

#### Create a Notification Schedule

- `POST /scheduling`
- **Success Response:** `200 OK` with the created resource as the response body.

| Parameter          | Type            | Description                                                 |
| :----------------- | :-------------- | :---------------------------------------------------------- |
| `message`          | `String`        | **Required**. The message to be delivered.                  |
| `recipientEmail`   | `String`        | **Required**. The recipient's email address.                |
| `recipientPhone`   | `String`        | The recipient's telephone number.                           |
| `deliveryDateTime` | `LocalDateTime` | **Required**. The date and time for delivery. (Format: `dd-MM-yyyy HH:mm:ss`) |

#### Find a Notification Schedule by ID

- `GET /scheduling/{id}`
- **Success Response:** `200 OK` with the found resource as the response body.
- **Failure Response:** `404 Not Found` if the ID does not exist.

#### Cancel a Notification Schedule by ID

- `DELETE /scheduling/{id}`
- **Success Response:** `202 Accepted`.
- **Failure Response:** `404 Not Found` if the ID does not exist.
