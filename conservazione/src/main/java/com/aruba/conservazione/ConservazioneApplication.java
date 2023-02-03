package com.aruba.conservazione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConservazioneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConservazioneApplication.class, args);
	}

}
