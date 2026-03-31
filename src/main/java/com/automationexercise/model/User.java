package com.automationexercise.model;

public record User(
        String title,
        String firstName,
        String lastName,
        String email,
        String password,
        String birthDay,
        String birthMonth,
        String birthYear,
        String company,
        String addressLine1,
        String addressLine2,
        String country,
        String state,
        String city,
        String zipCode,
        String mobileNumber
) {
    public String fullName() {
        return firstName + " " + lastName;
    }
}
