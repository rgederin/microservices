package com.gederin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PriceComparatorFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriceComparatorFrontApplication.class, args);
    }
}
