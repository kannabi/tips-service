package com.droptable.tipsservice.security;

import com.droptable.tipsservice.crm.exceptions.InvalidTokenException;
import com.droptable.tipsservice.crm.exceptions.WrongCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class CustomReactiveAuthenticationManager implements ReactiveAuthenticationManager {


	private final JwtTokenUtil jwtTokenUtil;
	private final ReactiveUserDetailsService userDetailsService;

	public CustomReactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
		Assert.notNull(userDetailsService, "userDetailsService cannot be null");
		Assert.notNull(jwtTokenUtil, "jwtTokenUtil cannot be null");

		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	public Mono<Authentication> authenticate(final Authentication authentication) {
		if (authentication instanceof JwtPreAuthenticationToken) {
			return Mono.just(authentication)
							.switchIfEmpty(Mono.defer(this::raiseBadCredentials))
							.cast(JwtPreAuthenticationToken.class)
							.flatMap(this::authenticateToken)
							.publishOn(Schedulers.parallel())
							.onErrorResume(e -> raiseBadCredentials())
							.map(u -> new JwtAuthenticationToken(u.getUsername(), u.getAuthorities()));
		}

		return Mono.just(authentication);
	}

	private <T> Mono<T> raiseBadCredentials() {
		return Mono.error(new WrongCredentialsException("Invalid Credentials!"));
	}

	private Mono<UserDetails> authenticateToken(final JwtPreAuthenticationToken jwtPreAuthenticationToken) {
		try {
			String authToken = jwtPreAuthenticationToken.getAuthToken();
			String username = jwtPreAuthenticationToken.getUsername();
			String bearerRequestHeader = jwtPreAuthenticationToken.getBearerRequestHeader();

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				if (jwtTokenUtil.validateToken(authToken)) {
					return this.userDetailsService.findByUsername(username)
									.publishOn(Schedulers.parallel())
									.switchIfEmpty(Mono.error(new InvalidTokenException("Token cannot be associated to an existing user!")));
				}
			}
		} catch (Exception e) {
			return Mono.error(new InvalidTokenException("Invalid token..."));
		}
		return Mono.empty();
	}
}
