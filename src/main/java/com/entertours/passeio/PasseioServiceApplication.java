package com.entertours.passeio;

import java.util.Collections;
import java.util.List;
import java.util.Iterator;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import com.entertours.passeio.utils.UserContextInterceptor;

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

			Files.lines(Paths.get("/home/luiz/entertours/.env")).forEach(line -> {
				String[] props = line.trim().split("=");
				if (props.length == 2) {
					System.setProperty(props[0], props[1]);
					// System.out.println(props[0] + " -> " + props[1]);
				}
			});

		};
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		if (interceptors == null) {
			template.setInterceptors(Collections.singletonList(
					new UserContextInterceptor()));
		} else {
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}

		return template;
	}
}
