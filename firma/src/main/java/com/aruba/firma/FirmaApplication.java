package com.aruba.firma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FirmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirmaApplication.class, args);
	}

}
