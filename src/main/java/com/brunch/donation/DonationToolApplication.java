package com.brunch.donation;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DonationToolApplication {
	public static void main(String[] args) {
		SpringApplication.run(DonationToolApplication.class, args);
	}
}
