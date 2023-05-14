package com.example.laterwithboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class LaterWithBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaterWithBootApplication.class, args);
	}

}
