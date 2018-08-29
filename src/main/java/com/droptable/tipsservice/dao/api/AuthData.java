package com.droptable.tipsservice.dao.api;

public class AuthData {
    private String login;
    private String password;

    public AuthData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthData() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
