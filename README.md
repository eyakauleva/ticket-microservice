
A simple sample of microservice on spring-boot.

## Description

Ticket service provides endpoints to receive **data related to events**.<br/>

This microservice is connected with another microservice via ***service registry pattern***. A key advantage of a microservices architecture is the ability to create new instances of each service to meet the current load, respond to failures and roll out upgrades. One side effect of this dynamic server-side environment is that IP address and port of a service instances change constantly. In order to route an API request to a particular service, the client or API gateway needs to find out the address of the service instance it should use. Whereas in the past it might have been feasible to record these locations in a config file, in a dynamic environment where instances are added and removed on the fly, an automated solution is needed. Service discovery provides a mechanism for keeping track of the available instances and distributing requests across them.

## Installation

1. Install Java 19 from https://www.oracle.com/java/technologies/downloads/
2. Install Maven 3 from https://maven.apache.org/download.cgi
3. Install PostgreSQL from https://www.postgresql.org/download/
4. Clone this repository:
```bash
$ git clone https://github.com/eyakauleva/ticket-microservice.git
```
5. Navigate to the project directory:
```bash
$ cd ticket-microservice
```
6. Start the application:
```bash
$ mvn spring-boot:run
```
7. App is ready to receive requests to ```http://localhost:9090/```


