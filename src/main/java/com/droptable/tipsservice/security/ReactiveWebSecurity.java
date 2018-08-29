package com.droptable.tipsservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;

@Configuration
@EnableWebFluxSecurity
@EnableWebSecurity
@EnableReactiveMethodSecurity
public class ReactiveWebSecurity {

	private ServerAuthenticationEntryPoint entryPoint = new UnauthorizedAuthenticationEntryPoint();
	private final JwtAuthenticationWebFilter jwtAuthenticationWebFilter;

	private static final String[] AUTH_WHITELIST = {
					"/resources/**",
					"/webjars/**",
					"/crm/auth",
					"/tips/tip_pay",
					"/crm/organizations/create"
//					"/crm/**"
	 };


	@Autowired
	public ReactiveWebSecurity(JwtAuthenticationWebFilter jwtAuthenticationWebFilter) {
		this.jwtAuthenticationWebFilter = jwtAuthenticationWebFilter;
	}


	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

	
		http
			.exceptionHandling()
			.authenticationEntryPoint(entryPoint)
			.and()
			.addFilterAt(this.jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
			.authorizeExchange()
			.pathMatchers(HttpMethod.OPTIONS).permitAll()
			.pathMatchers(AUTH_WHITELIST).permitAll()
			.anyExchange().authenticated()
			.and()
			.httpBasic().disable()
			.formLogin().disable()
			.csrf().disable()
			.logout().disable();
		return http.build();
	}

}
