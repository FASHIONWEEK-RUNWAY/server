package com.example.runway.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(
				"http://localhost:3000",
				"http://localhost:8080",
				"http://localhost:9000"
			)
			// 모든 HTTP Method를 허용한다.
			.allowedMethods("*", "PUT", "POST", "DELETE", "OPTIONS", "PATCH", "GET")
			// HTTP 요청의 Header에 어떤 값이든 들어갈 수 있도록 허용한다.
			.allowedHeaders("*")
			.exposedHeaders("Set-Cookie")
			// 자격증명 사용을 허용한다.
			// 해당 옵션 사용시 allowedOrigins를 * (전체)로 설정할 수 없다.
			.allowCredentials(true);
	}

}