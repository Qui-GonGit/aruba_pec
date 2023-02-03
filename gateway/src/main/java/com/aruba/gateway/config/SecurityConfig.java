package com.aruba.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity serverHttpSecurity) {
		serverHttpSecurity.csrf().disable().authorizeExchange(ex -> 
			ex.pathMatchers("/eureka/**","/auth/**").permitAll().anyExchange().authenticated()
		).oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
		return serverHttpSecurity.build();
	}
	@Bean
	public CorsWebFilter corsFilter() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);

	    return new CorsWebFilter(source);
	}
//	@Bean
//	public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
//		return new KeycloakSpringBootConfigResolver();
//	}
}
