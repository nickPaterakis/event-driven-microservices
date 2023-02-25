# Event Driven Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">
The event-driven-microservices project demonstrates the patterns that event-driven applications need. The patterns that have been implemented in the project are the Database per Service pattern, the Choreography-based Saga pattern, the CQRS pattern, the Domain event pattern, the Messaging pattern, the Outbox pattern (log tailing, polling publisher), and the API gateway pattern.

The technologies that the application uses: **Java 11, Spring Boot, Maven, Kafka, MongoDB, Docker, Debezium, Kong**.
# Architecture

![system (2)](https://user-images.githubusercontent.com/36018286/220418208-e58ee456-fc01-424e-967e-d4c7785b5581.png)

# Communication

Search properties request returns the properties based on the criteria that the user has selected. To find the properties that aren't reserved for the period of time that the user wants the property service needs reservation data. The property service has a reservation collection with all the required data that the request needs. The property service create and update the collection from the events that it consumes from the reservation service.

<p align="center">
  <img alt="GitHub" src="https://user-images.githubusercontent.com/36018286/219955824-8e8a6395-2c0d-4745-a5af-4f6056941d4b.png">
</p>
