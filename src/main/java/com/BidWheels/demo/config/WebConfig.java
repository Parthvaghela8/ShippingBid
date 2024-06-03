package com.BidWheels.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500","http://54.220.202.86:5500","http://54.220.202.86:8080","http://127.0.0.1:8080","https://127.0.0.1:5500","https://54.220.202.86:5500","https://54.220.202.86:8080","https://127.0.0.1:8080","https://shippingwars.projects.bbdgrad.com") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow only specific methods
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}

