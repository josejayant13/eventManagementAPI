package com.josejayant.EventManagementAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventManagementApiApplication 
{
	
	public static void main(String[] args) {
		SpringApplication.run(EventManagementApiApplication.class, args);

		System.out.println("API is running");
	
	}

}
