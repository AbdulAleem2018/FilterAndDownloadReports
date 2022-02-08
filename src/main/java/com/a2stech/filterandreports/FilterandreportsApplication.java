package com.a2stech.filterandreports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class FilterandreportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterandreportsApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("*").allowedMethods("POST", "GET", "PUT", "DELETE")
						.allowedOrigins("*");
			}
		};
	}
}