package com.gederin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductCatalogueApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogueApplication.class, args);
	}
}
