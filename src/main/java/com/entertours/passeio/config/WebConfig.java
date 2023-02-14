package com.entertours.passeio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// @EnableWebMvc
@Configuration
// @AutoConfigureAfter(DispatcherServletAutoConfiguration.class)
public class WebConfig implements WebMvcConfigurer {

	final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("*");
			}
		};
	}

	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {

	// 	final String URI = "file:/home/luiz/entertours/react-service/build/";

	// 	// registry.addResourceHandler("/").addResourceLocations(URI + "index.html");
	// 	registry.addResourceHandler("/**").addResourceLocations(URI);
	// 	registry.addResourceHandler("/img/**").addResourceLocations(URI + "/img/");
	// 	registry.addResourceHandler("/static/js/**").addResourceLocations(URI + "/static/js/");
	// 	registry.addResourceHandler("/static/css/**").addResourceLocations(URI + "/static/css/");
	// }

	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// 	registry.addViewController("/").setViewName("forward:/index.html");
	// }
	
}
