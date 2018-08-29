package com.droptable.tipsservice.dao.api;

import com.droptable.tipsservice.dao.db.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ApiRestaurant {

    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("login")
    private String login;

    @JsonProperty("business_account")
    private String accountBill;

    @JsonProperty("stuff_ids")
    private List<String> waiters;


    public ApiRestaurant(String id, String name, String email, String login, String accountBill, List<String> waiters) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.accountBill = accountBill;
        this.waiters = waiters;
    }

    public ApiRestaurant(Restaurant restaurant) {
        id = restaurant.getId();
        name = restaurant.getName();
        email = restaurant.getEmail();
        login = restaurant.getLogin();
        accountBill = restaurant.getAccountBill();

        List<String> ids = new ArrayList<>();

//        for (Waiter waiter: restaurant.getWaiters()) {
//            ids.add(waiter.getId());
//        }

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
