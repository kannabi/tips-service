package com.droptable.tipsservice.dao.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateWaiterRequest {

    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("second_name")
    private String secondName;

    @JsonProperty("third_name")
    private String thirdName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("bank_account")
    private String accountBill;

    public UpdateWaiterRequest(String id, String firstName, String secondName, String thirdName, String email, String accountBill) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.accountBill = accountBill;
    }

    public UpdateWaiterRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
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
