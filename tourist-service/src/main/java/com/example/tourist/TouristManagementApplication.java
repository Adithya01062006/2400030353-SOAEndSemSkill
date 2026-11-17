package com.example.tourist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TouristManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TouristManagementApplication.class, args);
    }
}
