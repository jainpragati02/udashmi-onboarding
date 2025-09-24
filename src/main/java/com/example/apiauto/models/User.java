package com.example.apiauto.models;

public class User {
    private String email;
    private String password;

    // No-argument constructor for Jackson deserialization
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // setters for Jackson deserialization
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
