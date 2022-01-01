package com.brunch.donation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableMongoRepositories
@SpringBootApplication
public class DonationToolApplication {
	public static void main(String[] args) {
		SpringApplication.run(DonationToolApplication.class, args);
	}
}
