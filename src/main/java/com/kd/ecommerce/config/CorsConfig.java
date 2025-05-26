package com.kd.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Define allowed origins explicitly (e.g., your frontend URL)
        List<String> allowedOrigins = Arrays.asList("http://localhost:4200", "https://your-deployed-frontend.com");
        config.setAllowedOriginPatterns(allowedOrigins); // ✅ Use allowedOriginPatterns()

        config.setAllowCredentials(true); // ✅ Required for authentication cookies & headers
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
