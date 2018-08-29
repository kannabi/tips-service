package com.droptable.tipsservice.crm.controllers;

import com.droptable.tipsservice.crm.services.CrmService;
import com.droptable.tipsservice.dao.api.ApiRestaurant;
import com.droptable.tipsservice.dao.api.AuthData;
import com.droptable.tipsservice.dao.api.JwtToken;
import com.droptable.tipsservice.dao.api.RestaurantSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/crm")
public class CrmController {

    private final CrmService crmService;

    @Autowired
    public CrmController(CrmService crmService) {
        this.crmService = crmService;
    }

//    @PostMapping()
//    @ResponseBody
//    public ApiRestaurant signUp(@RequestBody RestaurantSignUp restaurantSignUp) {
//        return crmService.createRestaurant(restaurantSignUp);
//    }

//    @PostMapping()
//    @ResponseBody
//    public ApiRestaurant auth(@RequestParam String login, @RequestParam String password) throws OrganizationNotFound {
//        ApiRestaurant apiRestaurant = crmService.getApiRestaurant(login);
//        if (null == apiRestaurant) {
//            throw new OrganizationNotFound("Organization not found");
//        }
//        return apiRestaurant;
//    }

    @PostMapping(
            value = "/auth",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<JwtToken>> signIn(@Valid @RequestBody AuthData data) {
        return crmService.signIn(data.getLogin(), data.getPassword())
                .map(ResponseEntity::ok);
    }

    @PostMapping(
            value = "/organizations/create",
            consumes =  MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ApiRestaurant>> signUp(@Valid @RequestBody RestaurantSignUp restaurantSignUp){
        return crmService.createRestaurant(restaurantSignUp).map(ResponseEntity::ok);
    }

    @GetMapping(
            value = "/organizations/me",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    public Mono<> getApiRestaurant(@RequestHeader(value = "Authorization") String bearerToken) {
    public Mono<ApiRestaurant> getApiRestaurant(String id) {
        return crmService.getApiRestaurant(id);
    }
}
