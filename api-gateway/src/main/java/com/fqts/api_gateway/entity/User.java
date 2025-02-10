package com.fqts.api_gateway.entity;

public class User {

    private String userName;
    private String userEmail;
    private String password;
    private String hasRole;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", hasRole='" + hasRole + '\'' +
                '}';
    }

    public User() {
    }

    public User(String userName, String userEmail, String password, String hasRole) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.hasRole = hasRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHasRole() {
        return hasRole;
    }

    public void setHasRole(String hasRole) {
        this.hasRole = hasRole;
    }
}
