package com.zeon.SpringBootAdminMicroServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAdminServer
@SpringBootApplication
public class SpringBootAdminMicroServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminMicroServerApplication.class, args);
	}

}
