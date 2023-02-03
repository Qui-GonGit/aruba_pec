package com.aruba.pec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:database.properties")
@EnableEurekaClient
public class PecApplication {

	public static void main(String[] args) {
		SpringApplication.run(PecApplication.class, args);
	}

}
