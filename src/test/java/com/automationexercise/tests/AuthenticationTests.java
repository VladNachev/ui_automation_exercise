package com.automationexercise.tests;

import com.automationexercise.base.BaseUiTest;
import com.automationexercise.flows.AuthenticationFlows;
import com.automationexercise.model.User;
import com.automationexercise.model.UserForIncorrectLogin;
import com.automationexercise.pages.AccountInformationPage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.utils.TestDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.automationexercise.listeners.AllureTestListener.class)
@Feature("Authentication")
public class AuthenticationTests extends BaseUiTest {

    private final AuthenticationFlows authenticationFlows = new AuthenticationFlows();

    @Test(description = "Test Case 1: Register User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Covers Automation Exercise test case 1 by creating a new user account and deleting it afterwards.")
    public void shouldRegisterUserSuccessfully() {
        User user = TestDataFactory.createUniqueUser();

        HomePage homePage = authenticationFlows.registerNewUser(user);
        Assert.assertTrue(homePage.isLoggedInAsVisible(user.fullName()), "Logged in user banner should contain the created user name");

        HomePage postDeletionHomePage = authenticationFlows.deleteCurrentAccount();
        Assert.assertTrue(postDeletionHomePage.isLoaded(), "User should return to the home page after deletion flow");
    }

    @Test(description = "Test Case 2: Login User with correct email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creates a fresh user as precondition, logs out, logs back in with valid credentials, and deletes the account.")
    public void shouldLoginWithCorrectCredentials() {
        User user = TestDataFactory.createUniqueUser();

        HomePage homePageAfterRegistration = authenticationFlows.registerNewUser(user);
        Assert.assertTrue(homePageAfterRegistration.isLoggedInAsVisible(user.fullName()), "User should be logged in immediately after registration");

        LoginPage loginPage = homePageAfterRegistration.clickLogout();
        Assert.assertTrue(loginPage.isLoginSectionVisible(), "Login page should be reachable after logout");

        HomePage homePageAfterLogin = authenticationFlows.login(user);
        Assert.assertTrue(homePageAfterLogin.isLoggedInAsVisible(user.fullName()), "Logged in user banner should contain the expected user name");

        HomePage postDeletionHomePage = authenticationFlows.deleteCurrentAccount();
        Assert.assertTrue(postDeletionHomePage.isLoaded(), "User should return to the home page after deletion flow");
    }

    @Test(description = "Test Case 3: Login User with incorrect email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to log in with invalid credentials and verify that the appropriate error message is displayed.")
    public void shouldDisplayErrorMessageWhenLoginWithIncorrectCredentials() {
        UserForIncorrectLogin user = new UserForIncorrectLogin();

        HomePage homePage = authenticationFlows.loginIncorrectCredentials(user);
        Assert.assertTrue(homePage.isIncorrectLoginMessageVisible(), "Incorrect login message should appear when logging in with invalid credentials");
    }

    @Test(description = "Test Case 4: Register user with existing email")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to register a new user using an email that already exists in the system and verify that the appropriate error message is displayed.")
    public void shouldDisplayErrorMessageWhenRegisteringWithExistingEmail() {
        User user = TestDataFactory.createUniqueUser();

        HomePage homePageAfterRegistration = authenticationFlows.registerNewUser(user);
        Assert.assertTrue(homePageAfterRegistration.isLoggedInAsVisible(user.fullName()), "User should be logged in immediately after registration");

        LoginPage loginPage = homePageAfterRegistration.clickLogout();
        Assert.assertTrue(loginPage.isLoginSectionVisible(), "Login page should be reachable after logout");

        loginPage.startSignup(user.fullName(), user.email());

        Assert.assertTrue(loginPage.isEmailAlreadyExistsMessageVisible(), "An error message about existing email should be visible when trying to register with the same email again");
    }

}
