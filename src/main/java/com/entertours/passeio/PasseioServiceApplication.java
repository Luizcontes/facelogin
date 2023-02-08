package com.entertours.passeio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
public class PasseioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasseioServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println(Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB --> free memory");
		};
	}
}
