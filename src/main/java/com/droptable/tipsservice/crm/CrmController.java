package com.droptable.tipsservice.crm;

import com.droptable.tipsservice.repositories.RestaurantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crm")
public class CrmController {

    private RestaurantsRepository restaurantsRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CrmController(RestaurantsRepository restaurantsRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.restaurantsRepository = restaurantsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
