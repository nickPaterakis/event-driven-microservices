# Event Driven Microservices <img alt="GitHub" src="https://img.shields.io/github/license/nickPaterakis/Booking-Microservices">

# System Overview
![system](https://user-images.githubusercontent.com/36018286/219954406-32699881-9994-4af7-a209-37139ca58786.png)

# Communication

Search properties request returns the properties based on the criteria that the user has selected. To find the properties that aren't reserved for the period of time that the user wants the property service needs reservation data. The property service has a reservation collection with all the required data that the request needs. We create and update the collection from the events that the property service consumes from the reservation service.

<p align="center">
  <img alt="GitHub" src="https://user-images.githubusercontent.com/36018286/219955824-8e8a6395-2c0d-4745-a5af-4f6056941d4b.png">
</p>
