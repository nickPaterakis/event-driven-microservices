# Event Driven Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">
## Introduction
The event-driven-microservices project demonstrates how to build loosely coupled, fault-tolerant, resilient, and highly-scalable microservices. The patterns implemented in the project include the Database-per-Service pattern, the Choreography-based Saga pattern, the CQRS pattern, the Domain Event pattern, the Messaging pattern, the Outbox pattern (log tailing, polling publisher), and the API Gateway pattern. The services architecture incorporates ideas from Hexagonal (Ports & Adapters) Architecture and Clean Architecture.

The project utilizes the following technologies: Java, Spring Boot, Maven, Kafka, MongoDB, Docker, Debezium, and Kong.
## System Architecture
The system consists of four microservices: Property Service, User Service, Reservation Service, and Notification Service. These microservices communicate with each other through Kafka, using the Messaging pattern. They produce messages with the Outbox pattern, which enables them to publish changes made to their local database to Kafka.

The Property Service and User Service use Debezium and the Transaction log tailing pattern to capture database changes and publish them as events to Kafka, implementing the Domain event pattern. The Reservation Service, on the other hand, uses the Polling Publisher pattern to periodically poll a  database collection for new changes and publish them to Kafka. This ensures that all microservices are notified of changes made to the database, maintaining data consistency across services.

MongoDB is used as the primary database for all services, providing a flexible and scalable NoSQL data store that is well-suited for microservices architectures. The Property Service and User Service also allow users to upload images, which are stored in Google Storage, a scalable and highly available blob storage service provided by Google Cloud Platform.

To facilitate communication between the frontend application and the microservices, the system incorporates an API Gateway pattern implemented using Kong. The API Gateway acts as a single entry point for all client requests, routing them to the appropriate microservices based on their URL paths. This allows the frontend application to communicate with the backend microservices in a standardized and simplified way, without having to know the details of each individual microservice's API. 

![architecture](https://user-images.githubusercontent.com/36018286/221354604-b56cd893-d141-4bcb-9f1b-03a45e9950d5.png)

## Service Architecture
The system's services architecture draws inspiration from both Hexagonal (Ports & Adapters) Architecture and Clean Architecture.

By using these patterns, the system achieves a high degree of modularity and separation of concerns, allowing for easier maintenance, testing, and evolution of the system over time. The Hexagonal (Ports & Adapters) Architecture focuses on defining clear boundaries between the core business logic of the system and the external interfaces (such as databases, APIs, or message queues). The Clean Architecture emphasizes the use of layers to organize the codebase into distinct modules that communicate through well-defined interfaces.

By combining these two patterns, the system is able to achieve a clean and flexible architecture that promotes good coding practices and helps ensure the system's long-term viability.

## Getting Started
### Prerequisites
Before running the application, ensure that you have installed the following tools:

1. Docker (version 19 or later)
2. Docker Compose (version 1.25 or later)
3. Maven (version 3.6.0 or later)

### Downloading the code
To download the code, you can either clone the repository using Git, or download the ZIP file and extract it to a directory on your local machine. Here are the steps to clone the repository:
1. Open a terminal or command prompt on your machine.
2. Navigate to the directory where you want to store the code.
3. Run the following command:

```console
git clone https://github.com/nickPaterakis/event-driven-microservices.git 
```

### Building the Docker Images
In order to build the Docker images, you first need to build the JAR files for each service. To do this, you will need to download and install Maven on your machine. Once you have Maven installed, navigate to the root directory of the project and run the following command:

```console
mvn install
```
This will build the JAR files for all services.

After building the JAR files, you can build the Docker images. To do this, follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the services/user-service directory.
3. Run the following command to build the Docker image for the user-service:
```console
docker build -t user-service .
```
Repeat steps 2-3 for the property-service, reservation-service, and notification-service directories to build the Docker images for those services.

### Running the application

Once the microservices are up and running, you can interact with the system by sending requests to the API gateway (which is exposed on port 8000).

Kong is running as an API gateway at http://localhost:8000 and you can access its admin API at http://localhost:8001. You can also use the Konga UI to manage Kong at http://localhost:1337.

The four microservices (user-service, property-service, reservation-service, and notificaiton-service) are running on ports 8082, 8088, 8085 and 8086 respectively. You can access their APIs via Kong at http://localhost:8000/user-service, http://localhost:8000/property-service, and http://localhost:8000/reservation-service.

You can use tools like Postman or curl to send requests to the API gateway.

That's it! You should now have a fully functional instance of the project running on your machine.


