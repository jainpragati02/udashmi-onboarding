package com.example.apiauto.util;

public class Config {
    // Base URL for Reqres public API
    public static final String BASE_URL = "https://reqres.in/api";
    public static final String API_KEY = "reqres-free-v1";

    // Reqres does not require real auth for GET users, but for testing purposes:
    public static final String USERNAME = "eve.holt@reqres.in"; // can be used in login POST
    public static final String PASSWORD = "cityslicka";          // can be used in login POST

    // Bearer token example (you can get it from login POST response)
    public static final String BEARER_TOKEN = "fake-token-for-demo";
}
