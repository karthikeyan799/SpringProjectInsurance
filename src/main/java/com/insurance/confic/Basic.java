package com.insurance.confic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class Basic {
//	package com.demo.config;
//    import org.springframework.context.annotation.Bean;
//	import org.springframework.context.annotation.Configuration;
//	import org.springframework.web.cors.CorsConfiguration;
//	import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//	import org.springframework.web.filter.CorsFilter;

//	@Configuration
//	public class CorsConfig {

	
	    @Bean
	    public CorsFilter corsFilter() {
	        // Create a configuration source and a CORS configuration
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();

	        // Allow requests from 'http://localhost:3000' (your frontend URL)
//	        config.addAllowedOrigin("http://localhost:3000");
//	        config.addAllowedOrigin("http://localhost:5173");
	        config.addAllowedOrigin("https://oldinsuranceproject.netlify.app");

	        
	        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
	        config.addAllowedMethod("*");

	        // Allow all headers in the request
	        config.addAllowedHeader("*");

	        // Register the CORS configuration for all endpoints (/**)
	        source.registerCorsConfiguration("/**", config);

	        // Create and return a CorsFilter with the configured settings
	        return new CorsFilter(source);
	    }
	
}
