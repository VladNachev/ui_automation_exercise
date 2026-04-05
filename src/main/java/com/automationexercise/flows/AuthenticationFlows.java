package com.automationexercise.flows;

import com.automationexercise.model.User;
import com.automationexercise.model.UserForIncorrectLogin;
import com.automationexercise.pages.AccountCreatedPage;
import com.automationexercise.pages.AccountDeletedPage;
import com.automationexercise.pages.AccountInformationPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationFlows {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFlows.class);

    @Step("Register a brand new user: {user.fullName()}")
    public HomePage registerNewUser(User user) {
        logger.info("Starting registration flow for {}", user.email());
        HomePage homePage = new HomePage().open();
        ensure(homePage.isLoaded(), "Home page should be visible");

        LoginPage loginPage = homePage.clickSignupLogin();
        ensure(loginPage.isSignupSectionVisible(), "Signup section should be visible");

        AccountInformationPage accountInformationPage = loginPage.startSignup(user.fullName(), user.email());
        ensure(accountInformationPage.isLoaded(), "Enter Account Information screen should be visible");

        AccountCreatedPage accountCreatedPage = accountInformationPage.register(user);
        ensure(accountCreatedPage.isVisible(), "Account Created screen should be visible");

        logger.info("Registration flow completed successfully for {}", user.email());
        return accountCreatedPage.clickContinue();
    }

    @Step("Log in as user {user.email()}")
    public HomePage login(User user) {
        logger.info("Starting login flow for {}", user.email());
        LoginPage loginPage = openLoginPage();
        HomePage resultPage = loginPage.login(user.email(), user.password());
        logger.info("Login flow submitted successfully for {}", user.email());
        return resultPage;
    }


    @Step("Attempt to log in with invalid credentials: {email}")
    public HomePage loginIncorrectCredentials(UserForIncorrectLogin user) {
        logger.info("Starting login flow with invalid credentials for {}", user.email());
        LoginPage loginPage = openLoginPage();
        HomePage resultPage = loginPage.login(user.email(), user.password());
        ensure(resultPage.isIncorrectLoginMessageVisible(), "Incorrect login message should be visible");
        logger.info("Login failed for {}", user.email());
        return resultPage;
    }

    @Step("Delete currently logged in account")
    public HomePage deleteCurrentAccount() {
        logger.info("Starting account deletion flow");
        AccountDeletedPage accountDeletedPage = new HomePage().clickDeleteAccount();
        ensure(accountDeletedPage.isVisible(), "Account Deleted screen should be visible");
        HomePage resultPage = accountDeletedPage.clickContinue();
        logger.info("Account deletion flow completed successfully");
        return resultPage;
    }

    private void ensure(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    private LoginPage openLoginPage() {
        HomePage homePage = new HomePage().open();
        ensure(homePage.isLoaded(), "Home page should be visible");

        LoginPage loginPage = homePage.clickSignupLogin();
        ensure(loginPage.isLoginSectionVisible(), "Login section should be visible");
        return loginPage;
    }
}
