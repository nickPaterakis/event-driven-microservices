# Event Driven Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">
The event-driven-microservices project demonstrates how to build loosely coupled, resilient, and highly scalable services. The patterns implemented in the project are the Database per Service pattern, the Choreography-based Saga pattern, the CQRS pattern, the Domain event pattern, the Messaging pattern, the Outbox pattern (log tailing, polling publisher), and the API gateway pattern.

The project uses the following technologies: Java, Spring Boot, Maven, Kafka, MongoDB, Docker, Debezium, Kong.
# System Architecture
![architecture](https://user-images.githubusercontent.com/36018286/221354604-b56cd893-d141-4bcb-9f1b-03a45e9950d5.png)

# Service Architecture
The services use ideas from Hexagonal (Ports & Adapters) Architecture and Clean Architecture. 

The Hexagonal architecture helps to keep the business layer independent from the framework, UI, streaming systems like Kafka, and databases. This gives you the flexibility to do changes on the adapters easily. For example, you can change the database that you use without affecting the other system. With this pattern, the core logic of your system is isolated and you can do changes on your business logic without a significant impact on your codebase. This architecture makes your application more testable, maintainable, changeable, and easy to develop.
