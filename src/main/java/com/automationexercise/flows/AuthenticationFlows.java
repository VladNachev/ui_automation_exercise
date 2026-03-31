package com.automationexercise.flows;

import com.automationexercise.model.User;
import com.automationexercise.pages.AccountCreatedPage;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.pages.AccountInformationPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import io.qameta.allure.Step;

public class AuthenticationFlows {

    @Step("Register a brand new user: {user.fullName()}")
    public HomePage registerNewUser(User user) {
        HomePage homePage = new HomePage().open();
        ensure(homePage.isLoaded(), "Home page should be visible");

        LoginPage loginPage = homePage.clickSignupLogin();
        ensure(loginPage.isSignupSectionVisible(), "Signup section should be visible");

        AccountInformationPage accountInformationPage = loginPage.startSignup(user.fullName(), user.email());
        ensure(accountInformationPage.isLoaded(), "Enter Account Information screen should be visible");

        AccountCreatedPage accountCreatedPage = accountInformationPage.register(user);
        ensure(accountCreatedPage.isVisible(), "Account Created screen should be visible");

        return accountCreatedPage.clickContinue();
    }

    @Step("Log in as user {user.email()}")
    public HomePage login(User user) {
        HomePage homePage = new HomePage().open();
        ensure(homePage.isLoaded(), "Home page should be visible");

        LoginPage loginPage = homePage.clickSignupLogin();
        ensure(loginPage.isLoginSectionVisible(), "Login section should be visible");
        return loginPage.login(user.email(), user.password());
    }

    @Step("Delete currently logged in account")
    public HomePage deleteCurrentAccount() {
        AccountDeletedPage accountDeletedPage = new HomePage().clickDeleteAccount();
        ensure(accountDeletedPage.isVisible(), "Account Deleted screen should be visible");
        return accountDeletedPage.clickContinue();
    }

    private void ensure(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
