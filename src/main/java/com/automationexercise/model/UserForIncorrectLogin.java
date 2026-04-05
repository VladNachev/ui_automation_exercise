package com.automationexercise.model;

public record UserForIncorrectLogin(
        String email,
        String password
) {
    public UserForIncorrectLogin() {
        this("dummyEmail@mail.com", "dummyPassword123");
    }
}