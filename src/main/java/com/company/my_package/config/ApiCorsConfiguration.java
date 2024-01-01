package com.company.my_package.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global application configuration.
 */
@Configuration
public class ApiCorsConfiguration {

    @Value("${application.front.url:}")
    private String frontUrl;

    /**
     * CORS configuration.
     *
     * @return configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(frontUrl)
                        .allowedMethods("GET", "POST", "DELETE");
            }
        };
    }

}

