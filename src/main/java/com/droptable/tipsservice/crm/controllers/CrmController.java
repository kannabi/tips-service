package com.droptable.tipsservice.crm.controllers;

import com.droptable.tipsservice.crm.services.CrmService;
import com.droptable.tipsservice.dao.api.*;
import com.droptable.tipsservice.dao.db.Waiter;
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
            value = "/organizations",
            consumes =  MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ResponseEntity<ApiRestaurant>> signUp(@Valid @RequestBody RestaurantSignUp restaurantSignUp){
        return crmService.createRestaurant(restaurantSignUp).map(ResponseEntity::ok);
    }

    @GetMapping(
            value = "/organizations/me",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<ApiRestaurant> getApiRestaurant(@RequestHeader(value = "Authorization") String bearerToken) {
        return crmService.getApiRestaurant(bearerToken);
    }

    @PutMapping(
            value = "/organizations/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<UpdateRestaurantResponse> updateRestaurant(@Valid @RequestBody RestaurantUpdate restaurantUpdate) {
        return crmService.updateRestaurant(restaurantUpdate)
                .map(UpdateRestaurantResponse::new);
    }

    @DeleteMapping(
            value = "/organizations/update",
            consumes=MediaType.APPLICATION_JSON_VALUE
    )
    public void deleteRestaurant(@RequestBody Id id) {
        crmService.deleteRestaurant(id.getId());
    }

    @PostMapping(
            value = "/stuffs/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Waiter> createWaiter(@RequestBody WaiterCreateRequest request) {
        return crmService.createWaiter(request);
    }

    @GetMapping(
        value = "/stuffs",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<WaitersResponse> getWaiters(
            @RequestParam("organization_id") String organizationId,
            @RequestParam("page") int page,
            @RequestParam("per_page") int perPage
    ) {
        return crmService.getWaiters(organizationId, page, perPage)
                .map(waitersPage ->
                    new WaitersResponse(
                        waitersPage.getContent(),
                        page,
                        waitersPage.getTotalPages()
                    )
                );
    }
}
