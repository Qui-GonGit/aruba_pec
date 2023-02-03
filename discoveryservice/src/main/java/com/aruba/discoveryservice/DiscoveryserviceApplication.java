package com.aruba.discoveryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.aruba.discoveryservice.config.SecurityConfig;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryserviceApplication {
	private static final Logger logger = LoggerFactory.getLogger(DiscoveryserviceApplication.class);
	public static void main(String[] args) {
		logger.debug("init Discovery Service Eureka");
		SpringApplication.run(DiscoveryserviceApplication.class, args);
	}

}
