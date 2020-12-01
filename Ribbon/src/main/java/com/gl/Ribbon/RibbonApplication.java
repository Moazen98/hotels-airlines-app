package com.gl.Ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RibbonClient(name = "load", configuration = RibbonConfiguration.class)
public class RibbonApplication {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/")
	public String ribbonPing() {
		return "hello";
	}

	@GetMapping("/invok")
	public String invokPort() {
		String url = "http://load/location";
		return restTemplate.getForObject(url, String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}

}
