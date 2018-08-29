package com.droptable.tipsservice.dao.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantSignUp {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("login")
    private String login;

    @JsonProperty("business_account")
    private String accountBill;

    @JsonProperty("password")
    private String password;

    public RestaurantSignUp(String name, String email, String login, String accountBill, String password) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.accountBill = accountBill;
        this.password = password;
    }

    public RestaurantSignUp() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
