package com.droptable.tipsservice.dao.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantUpdate {

    private String id;

    private String name;

    private String email;

    @JsonProperty("business_account")
    private String accountBill;

    public RestaurantUpdate(String id, String name, String email, String accountBill) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountBill = accountBill;
    }

    public RestaurantUpdate() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAccountBill() {
        return accountBill;
    }

    public void setAccountBill(String accountBill) {
        this.accountBill = accountBill;
    }
}
