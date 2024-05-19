package com.BidWheels.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        // Specify the allowed origins
        config.addAllowedOrigin("http://127.0.0.1:5500");
        config.addAllowedOrigin("http://54.220.202.86:5500");
        config.addAllowedOrigin("http://54.220.202.86:8080");
        config.addAllowedOrigin("http://127.0.0.1:8080");
        config.addAllowedOrigin("https://127.0.0.1:5500");
        config.addAllowedOrigin("https://54.220.202.86:5500");
        config.addAllowedOrigin("https://54.220.202.86:8080");
        config.addAllowedOrigin("https://127.0.0.1:8080");
        config.addAllowedOrigin("https://shippingwars.projects.bbdgrad.com");

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}




//package com.BidWheels.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class WebConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("*"); // you can replace * with specific origins
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//}
//



//
////package com.BidWheels.demo.config;
////
////import org.springframework.context.annotation.Configuration;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////
////@Configuration
////public class WebConfig implements WebMvcConfigurer {
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("http://127.0.0.1:5500","http://54.220.202.86:5500","http://54.220.202.86:8080","http://127.0.0.1:8080","https://127.0.0.1:5500","https://54.220.202.86:5500","https://54.220.202.86:8080","https://127.0.0.1:8080","https://shippingwars.projects.bbdgrad.com") // Allow requests from any origin
////                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow only specific methods
////                .allowCredentials(true)
////                .allowedHeaders("*");
////    }
////}
////
