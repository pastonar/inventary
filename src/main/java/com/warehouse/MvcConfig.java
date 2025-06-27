package com.warehouse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") 
			.allowedOrigins("*") // http://localhost:8080 http://192.168.103.131:5501,http://localhost:8080,http://localhost:5501
			.allowedMethods("HEAD", "GET","PUT", "POST","DELETE", "PATCH") // Allowed HTTP methods
			.allowedHeaders("*") // Allowed request headers
			.maxAge(3000); 
			//.allowCredentials(true) 
			//.maxAge(20000)
		//System.out.println("addCorsMappings"); 
	}
	
}
