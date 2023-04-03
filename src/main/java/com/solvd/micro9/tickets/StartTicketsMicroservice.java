package com.solvd.micro9.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StartTicketsMicroservice {

    public static void main(String[] args) {
        SpringApplication.run(StartTicketsMicroservice.class, args);
    }

}