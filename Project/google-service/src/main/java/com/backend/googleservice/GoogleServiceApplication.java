package com.backend.googleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GoogleServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoogleServiceApplication.class, args);
	}

}
