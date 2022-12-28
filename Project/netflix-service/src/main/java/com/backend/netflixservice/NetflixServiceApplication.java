package com.backend.netflixservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NetflixServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixServiceApplication.class, args);
	}

}
