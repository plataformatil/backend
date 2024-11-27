package com.til.vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.til.vagas")
public class VagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VagasApplication.class, args);
	}

}