package com.droptable.tipsservice.crm.services;

import com.droptable.tipsservice.repositories.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;


/**
 * When a user tries to authenticate (sign in) this service is used inside
 * @AuthenticationManagerBuilder and retrieves a User object for SpringBoot Security, checks the password etc.
 *
 * Important note that the User constructor here is not the User we use throughout the application, but a User object
 * user only inside the security framework. It holds only username and password.
 */
@Service
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

	private final RestaurantsRepository restaurantRepository;

	@Autowired
	public ReactiveUserDetailsServiceImpl(RestaurantsRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Mono<UserDetails> findByUsername(String login) {
		return Mono.just(restaurantRepository.findByLogin(login))
				.map(restaurant -> new User(restaurant.getLogin(), restaurant.getPassword(), new ArrayList<>()));
	}

}
