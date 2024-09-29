package com.warehouse;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;







import java.util.Set;
/*
@SpringBootApplication
public class InventaryApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InventaryApplication.class, args);
	}

		 
}
*/

@SpringBootApplication
public class InventaryApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InventaryApplication.class, args);
	}
	@Configuration
	public static class Myconfiguration{
        @Bean
        WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
                }
            };
        }
	}
}




