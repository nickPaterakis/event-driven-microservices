# Event Driven Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">
## Overview
The event-driven-microservices project demonstrates how to build loosely coupled, fault-tolerant, resilient, and highly-scalable microservices. The patterns implemented in the project include the Database-per-Service pattern, the Choreography-based Saga pattern, the CQRS pattern, the Domain Event pattern, the Messaging pattern, the Outbox pattern (log tailing, polling publisher), and the API Gateway pattern. The services architecture incorporates ideas from Hexagonal (Ports & Adapters) Architecture and Clean Architecture.

The project utilizes the following technologies: Java, Spring Boot, Maven, Kafka, MongoDB, Docker, Debezium, and Kong.
## System Architecture
The system consists of four microservices: Property Service, User Service, Reservation Service, and Notification Service. These microservices communicate with each other through Kafka, using the Messaging pattern. They produce messages with the Outbox pattern, which enables them to publish changes made to their local database to Kafka.

The Property Service and User Service use Debezium and the Transaction log tailing pattern to capture database changes and publish them as events to Kafka, implementing the Domain event pattern. The Reservation Service, on the other hand, uses the Polling Publisher pattern to periodically poll a  database collection for new changes and publish them to Kafka. This ensures that all microservices are notified of changes made to the database, maintaining data consistency across services.

MongoDB is used as the primary database for all services, providing a flexible and scalable NoSQL data store that is well-suited for microservices architectures. The Property Service and User Service also allow users to upload images, which are stored in Google Storage, a scalable and highly available blob storage service provided by Google Cloud Platform.

To facilitate communication between the frontend application and the microservices, the system incorporates an API Gateway pattern implemented using Kong. The API Gateway acts as a single entry point for all client requests, routing them to the appropriate microservices based on their URL paths. This allows the frontend application to communicate with the backend microservices in a standardized and simplified way, without having to know the details of each individual microservice's API. 

![architecture](https://user-images.githubusercontent.com/36018286/221354604-b56cd893-d141-4bcb-9f1b-03a45e9950d5.png)

## System's Services Architecture
The system's services architecture draws inspiration from both Hexagonal (Ports & Adapters) Architecture and Clean Architecture.

By using these patterns, the system achieves a high degree of modularity and separation of concerns, allowing for easier maintenance, testing, and evolution of the system over time. The Hexagonal (Ports & Adapters) Architecture focuses on defining clear boundaries between the core business logic of the system and the external interfaces (such as databases, APIs, or message queues). The Clean Architecture emphasizes the use of layers to organize the codebase into distinct modules that communicate through well-defined interfaces.

By combining these two patterns, the system is able to achieve a clean and flexible architecture that promotes good coding practices and helps ensure the system's long-term viability.

## Getting Started
### Prerequisites
Before running the application, ensure that you have installed the following tools:

1. Docker 
2. Docker Compose
3. Java
4. Maven 

### Setting Up the Project
Follow the steps below to set up the project on your local machine:

<b>Step 1: Clone the Repository</b>

Open a terminal window and navigate to the directory where you want to store the project. Then, clone the repository by running:

```
git clone https://github.com/nickPaterakis/event-driven-microservices.git 
```

<b>Step 2: Build the Project</b>

Navigate to the root directory of the project using the terminal and run:

```
mvn install
```
This command will download all the necessary dependencies and build JAR files for each service.

<b>Step 3: Build Docker Images</b>

Each service in the project has a corresponding Dockerfile. To build these images, navigate to each service directory and run the following command:

```
docker build -t <image-name> .
```
Replace <image-name> with the appropriate name for each service. Repeat this step for each service.

<b>Step 4: Set up Google Cloud Storage</b>

This application uses Google Cloud Storage for storing and retrieving images. To set up Google Cloud Storage:1. Create a Google Cloud account.

2. Create a storage bucket and ensure it's publicly accessible.
3. Create a service account with appropriate permissions (e.g., "Storage Admin").
4. Download the service account key and rename it to gcp-account-file.json.
5. Place the key in the resources directories of the property and user services.

### Running the application

The process of running the application involves starting the services, configuring MongoDB as a replica set for Debezium, and finally, configuring Debezium itself. The steps below provide a detailed guide:

<b>Step 1: Start the Services</b>

Navigate to the directory containing the docker-compose.yml file by running the following command:
  
```
cd infrastructure/docker
```

Next, start all the services defined in the docker-compose.yml file by running the following command:
```
docker-compose up -d
```
The -d flag will start the containers in detached mode, which means they'll run in the background.

<b>Step 2: Configure MongoDB Replica Set</b>

While the services are starting, MongoDB needs to be configured as a replica set for Debezium to work correctly. The configuration script for this is located in infrastructure/debezium/debezium-mongo-setup.sh.

First, ensure that the script is executable. Navigate to the infrastructure/debezium directory and run the following command:

```
chmod +x debezium-mongo-setup.sh
```

Then, execute the script to set up MongoDB as a replica set:
```
./debezium-mongo-setup.sh
```
This script will set up MongoDB as a replica set, which is necessary for Debezium to capture changes.

<b>Step 3: Configure Debezium</b>

After setting up MongoDB as a replica set, Debezium itself needs to be configured. This can be done using the send-config-to-debezium.sh script located in the infrastructure/debezium directory.

First, ensure that this script is also executable:
```
chmod +x send-config-to-debezium.sh
```

Then, run the script:
```
./send-config-to-debezium.sh
```
This script will send the necessary configurations to Debezium.

<b>Step 4: Access the Application</b>

Once the microservices are up and running, you can interact with the system by sending requests to the API gateway (which is exposed on port 8000).

Kong is running as an API gateway at http://localhost:8000 and you can access its admin API at http://localhost:8001. You can also use the Konga UI to manage Kong at http://localhost:1337.

The four microservices (user-service, property-service, reservation-service, and notificaiton-service) are running on ports 8082, 8088, 8085 and 8086 respectively. You can access their APIs via Kong at http://localhost:8000/user-service, http://localhost:8000/property-service, and http://localhost:8000/reservation-service.

You can use tools like Postman or curl to send requests to the API gateway.

That's it! You should now have a fully functional instance of the project running on your machine.


