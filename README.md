# Event Driven Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">
The event-driven-microservices project demonstrates how to build loosely coupled, fault-tolerant, resilient, and highly scalable services. The project implements several patterns, including the Database per Service pattern, the Choreography-based Saga pattern, the CQRS pattern, the Domain event pattern, the Messaging pattern, the Outbox pattern (log tailing, polling publisher), and the API gateway pattern.

The project uses the following technologies: Java, Spring Boot, Maven, Kafka, MongoDB, Docker, Debezium, Kong.
# System Architecture
The system consists of four microservices: Property Service, User Service, Reservation Service, and Notification Service. These microservices communicate with each other through Kafka, using the Messaging pattern. They produce messages with the Outbox pattern, which enables them to publish changes made to their local database to Kafka.

The Property Service and User Service use Debezium and the Transaction log tailing pattern to capture database changes and publish them as events to Kafka, implementing the Domain event pattern. The Reservation Service, on the other hand, uses the Polling Publisher pattern to periodically poll a  database collection for new changes and publish them to Kafka. This ensures that all microservices are notified of changes made to the database, maintaining data consistency across services.

MongoDB is used as the primary database for all services, providing a flexible and scalable NoSQL data store that is well-suited for microservices architectures. The Property Service and User Service also allow users to upload images, which are stored in Google Storage, a scalable and highly available blob storage service provided by Google Cloud Platform.

To facilitate communication between the frontend application and the microservices, the system incorporates an API Gateway pattern implemented using Kong. The API Gateway acts as a single entry point for all client requests, routing them to the appropriate microservices based on their URL paths. This allows the frontend application to communicate with the backend microservices in a standardized and simplified way, without having to know the details of each individual microservice's API. 

![architecture](https://user-images.githubusercontent.com/36018286/221354604-b56cd893-d141-4bcb-9f1b-03a45e9950d5.png)

# Service Architecture
The services architecture incorporates ideas from both Hexagonal (Ports & Adapters) Architecture and Clean Architecture.

The Hexagonal architecture helps to maintain the independence of the business layer from the framework, UI, streaming systems like Kafka, and databases. This allows for easy changes to be made to adapters. For example, you can change the database you use without affecting other systems. With this pattern, the core logic of your system is isolated, enabling changes to be made to your business logic without a significant impact on your codebase. This architecture makes your application more testable, maintainable, changeable, and easier to develop.

Clean Architecture is a software design philosophy that promotes the separation of concerns, maintainability, and testability. At its core, Clean Architecture aims to create software that is easy to modify, update, and maintain over time. The architecture is organized around a set of layers, with each layer having a specific responsibility and level of abstraction. The layers are arranged in a hierarchical manner, with the business logic at the center and the UI, frameworks, and infrastructure on the outer layers. The architecture emphasizes the use of interfaces and dependency inversion to reduce coupling between components and to make the codebase more flexible and adaptable.
