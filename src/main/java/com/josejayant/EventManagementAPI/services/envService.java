package com.josejayant.EventManagementAPI.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class envService {

    @Value("${spring.datasource.url:NOT SET}")
    private String datasourceUrl;

    @Value("${spring.datasource.username:NOT SET}")
    private String datasourceUsername;

    @Value("${spring.datasource.password:NOT SET}")
    private String datasourcePassword;

    @Bean
    public CommandLineRunner printProperties() {
        return args -> {
            System.out.println("-----------------------------------------------------");
            System.out.println("Resolved spring.datasource.url: " + datasourceUrl);
            System.out.println("Resolved spring.datasource.username: " + datasourceUsername);
            System.out.println("Resolved spring.datasource.password: " + datasourcePassword);
            System.out.println("-----------------------------------------------------");

        };
    }
}