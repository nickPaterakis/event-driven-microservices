# Event Driven Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">
The event-driven-microservices project demonstrates how to build loosely coupled, resilient, and highly scalable services. The project implements several patterns, including the Database per Service pattern, the Choreography-based Saga pattern, the CQRS pattern, the Domain event pattern, the Messaging pattern, the Outbox pattern (log tailing, polling publisher), and the API gateway pattern.

The project uses the following technologies: Java, Spring Boot, Maven, Kafka, MongoDB, Docker, Debezium, Kong.
# System Architecture
![architecture](https://user-images.githubusercontent.com/36018286/221354604-b56cd893-d141-4bcb-9f1b-03a45e9950d5.png)

# Service Architecture
The services architecture incorporates ideas from both Hexagonal (Ports & Adapters) Architecture and Clean Architecture.

The Hexagonal architecture helps to maintain the independence of the business layer from the framework, UI, streaming systems like Kafka, and databases. This allows for easy changes to be made to adapters. For example, you can change the database you use without affecting other systems.

With this pattern, the core logic of your system is isolated, enabling changes to be made to your business logic without a significant impact on your codebase. This architecture makes your application more testable, maintainable, changeable, and easier to develop.
