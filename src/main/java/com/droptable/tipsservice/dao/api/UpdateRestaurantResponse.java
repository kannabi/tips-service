package com.droptable.tipsservice.dao.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRestaurantResponse {
    @JsonProperty("organization")
    private ApiRestaurant apiRestaurant;

    public UpdateRestaurantResponse(ApiRestaurant apiRestaurant) {
        this.apiRestaurant = apiRestaurant;
    }

    public UpdateRestaurantResponse() {
    }

    public ApiRestaurant getApiRestaurant() {
        return apiRestaurant;
    }

    public void setApiRestaurant(ApiRestaurant apiRestaurant) {
        this.apiRestaurant = apiRestaurant;
    }
}
