package com.entertours.passeio;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import com.entertours.passeio.utils.UserContextInterceptor;

@SpringBootApplication
@EnableFeignClients
public class PasseioServiceApplication {
	
	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(PasseioServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println(Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB --> free memory");

			String[] beans = applicationContext.getBeanDefinitionNames();
			for (String bean : beans) {
				System.out.println(bean);
			}
		};
	}

	// @LoadBalanced
	// @Bean
	// public RestTemplate getRestTemplate() {
	// 	RestTemplate template = new RestTemplate();
	// 	List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
	// 	if (interceptors == null) {
	// 		template.setInterceptors(Collections.singletonList(
	// 				new UserContextInterceptor()));
	// 	} else {
	// 		interceptors.add(new UserContextInterceptor());
	// 		template.setInterceptors(interceptors);
	// 	}

	// 	return template;
	// }
}
