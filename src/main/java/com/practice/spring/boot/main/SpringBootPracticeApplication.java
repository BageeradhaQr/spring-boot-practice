package com.practice.spring.boot.main;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "User Management API",
				description = "User Management API",
				version = "v1.0",
				contact = @Contact(
						name = "Bageeradha Vajja",
						email = "bageeradhavajja@gmail.com",
						url = "https://www.enticeinfotech.com"
				),
				license = @License(
						name = "Apache 3.0",
						url = "https://www.enticeinfotech.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "User Management Documentation",
				url = "https://www.enticeinfotech.com/index.html"
		)
)
public class SpringBootPracticeApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticeApplication.class, args);
	}

}
