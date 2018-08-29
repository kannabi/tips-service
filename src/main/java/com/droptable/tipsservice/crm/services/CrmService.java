package com.droptable.tipsservice.crm.services;

import com.droptable.tipsservice.crm.exceptions.OrganizationAlreadyExist;
import com.droptable.tipsservice.crm.exceptions.OrganizationNotFound;
import com.droptable.tipsservice.crm.exceptions.WrongCredentialsException;
import com.droptable.tipsservice.dao.api.ApiRestaurant;
import com.droptable.tipsservice.dao.api.JwtToken;
import com.droptable.tipsservice.dao.api.RestaurantSignUp;
import com.droptable.tipsservice.dao.db.Restaurant;
import com.droptable.tipsservice.repositories.RestaurantsRepository;
import com.droptable.tipsservice.security.CustomPasswordEncoder;
import com.droptable.tipsservice.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CrmService {

    private final RestaurantsRepository restaurantsRepository;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomPasswordEncoder passwordEncryptor;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public CrmService(
            RestaurantsRepository restaurantsRepository,
//            BCryptPasswordEncoder bCryptPasswordEncoder,
            CustomPasswordEncoder passwordEncryptor,
            JwtTokenUtil jwtTokenUtil
    ) {
        this.restaurantsRepository = restaurantsRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordEncryptor = passwordEncryptor;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public Mono<ApiRestaurant> createRestaurant(RestaurantSignUp restaurantSignUp) throws OrganizationAlreadyExist {
        Restaurant restaurant = restaurantsRepository.findByLogin(restaurantSignUp.getLogin());
        if (restaurant != null) {
            throw new OrganizationAlreadyExist("Organization already exist");
        }
        restaurant = restaurantsRepository.save(
                new Restaurant(
                        restaurantSignUp.getName(),
                        restaurantSignUp.getEmail(),
                        restaurantSignUp.getLogin(),
                        restaurantSignUp.getAccountBill(),
                        passwordEncryptor.encode(restaurantSignUp.getPassword()),
                        new ArrayList<>()
                )
        );

        return Mono.just(new ApiRestaurant(restaurant));
    }

    public Mono<JwtToken> signIn(String login, String password) {
        Restaurant restaurant = restaurantsRepository.findByLogin(login);
        if (null == restaurant) {
            throw new WrongCredentialsException("Wrong login. User not found");
        }

        if (!passwordEncryptor.matches(password, restaurant.getPassword())) {
            throw new WrongCredentialsException("Wrong password");
        }

        return Mono.just(
            new JwtToken(
                jwtTokenUtil.generateToken(
                    new User(restaurant.getLogin(), restaurant.getPassword(), new ArrayList<>())
                )
            )
        );
    }

    public Mono<ApiRestaurant> getApiRestaurant(String id) {
        Optional<Restaurant> restaurantOptional = restaurantsRepository.findById(id);
        if (!restaurantOptional.isPresent()) {
            throw new OrganizationNotFound("Organization didn't found");
        }
        return Mono.just(new ApiRestaurant(restaurantOptional.get()));
    }
}