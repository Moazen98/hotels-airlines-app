package com.zeon.UserPortable;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

@SpringBootApplication()
@EnableDiscoveryClient
@EnableCircuitBreaker
public class UserPortableApplication {

	//private final static String QUEUE_NAME = "userRequestDelete";

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(UserPortableApplication.class, args);


	}	

	@Bean
	public RestTemplate getRetRestTemplate() {
		return new RestTemplate();
	}

}
