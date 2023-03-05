
A simple sample of microservice on spring-boot.

## Description

Ticket service provides endpoints to receive **data related to events**.<br/>

It is connected with another microservice via **service registry pattern**. The Service Registry is a database for microservice instances. 
When the client service need to access internal services, then it will query from Service Registry and access them.
Here implementation is done via Netflix Eureka.

<p align="center">
  <img src="http://1.bp.blogspot.com/-dXDcC3rnVeU/V7J79R5XxII/AAAAAAAADOQ/K53Ebgvj0uweGLXDeVKanQa3uFxxxRCvwCK4B/s1600/img.png" width="420" height="270">
</p>

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


