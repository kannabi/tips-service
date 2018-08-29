package com.droptable.tipsservice.dao.api;

/**
 * Because I have web-monkey at the front
 */
public class ApiRestaurantWrapper {
    private ApiRestaurant organization;

    public ApiRestaurantWrapper(ApiRestaurant organization) {
        this.organization = organization;
    }

    public ApiRestaurantWrapper() {
    }

    public ApiRestaurant getOrganization() {
        return organization;
    }

    public void setOrganization(ApiRestaurant organization) {
        this.organization = organization;
    }
}
