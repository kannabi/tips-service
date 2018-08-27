package com.droptable.tipsservice.dao.api;

import com.droptable.tipsservice.dao.db.Restaurant;
import com.droptable.tipsservice.dao.db.Waiter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ApiRestaurant {

    @JsonProperty("name")
    private String name;

    @JsonProperty("name")
    private String email;

    @JsonProperty("name")
    private String login;

    @JsonProperty("business_account")
    private String accountBill;

    @JsonProperty("stuff_ids")
    private List<String> waiters;

    public ApiRestaurant(String name, String email, String login, String accountBill, List<String> waiters) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.accountBill = accountBill;
        this.waiters = waiters;
    }

    public ApiRestaurant(Restaurant restaurant) {
        name = restaurant.getName();
        email = restaurant.getEmail();
        login = restaurant.getLogin();
        accountBill = restaurant.getAccountBill();

        List<String> ids = new ArrayList<>();

        for (Waiter waiter: restaurant.getWaiters()) {
            ids.add(waiter.getId());
        }

        waiters = ids;
    }

    public ApiRestaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAccountBill() {
        return accountBill;
    }

    public void setAccountBill(String accountBill) {
        this.accountBill = accountBill;
    }

    public List<String> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<String> waiters) {
        this.waiters = waiters;
    }
}
