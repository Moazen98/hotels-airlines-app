package com.zeon.HotelReservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zeon.HotelReservation.Modal.Hotel;
import com.zeon.HotelReservation.Repository.HotelRepository;
import com.zeon.HotelReservation.Services.HotelService;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class HotelReservationApplication {
	
	@Autowired
	static HotelService hs;
	
	public static void main(String[] args) {
		SpringApplication.run(HotelReservationApplication.class, args);

	}
	
	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

}
