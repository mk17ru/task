package com.example.demo.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {
	@Bean
	public OpenAPI swagger() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTFul API сервис для игры в Тайного Санту.")
						.description("Документация по API"));
	}
}
