package com.automationexercise.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final By LOGIN_HEADER = By.xpath("//h2[contains(.,'Login to your account')]");
    private static final By SIGNUP_HEADER = By.xpath("//h2[contains(.,'New User Signup!')]");
    private static final By LOGIN_EMAIL_INPUT = By.cssSelector("input[data-qa='login-email']");
    private static final By LOGIN_PASSWORD_INPUT = By.cssSelector("input[data-qa='login-password']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[data-qa='login-button']");
    private static final By SIGNUP_NAME_INPUT = By.cssSelector("input[data-qa='signup-name']");
    private static final By SIGNUP_EMAIL_INPUT = By.cssSelector("input[data-qa='signup-email']");
    private static final By SIGNUP_BUTTON = By.cssSelector("button[data-qa='signup-button']");
    private static final By EMAIL_ALREADY_EXISTS_MESSAGE = By.xpath("//p[contains(text(),'Email Address already exist!')]");

    public boolean isLoginSectionVisible() {
        return isVisible(LOGIN_HEADER);
    }

    public boolean isSignupSectionVisible() {
        return isVisible(SIGNUP_HEADER);
    }

    public HomePage login(String email, String password) {
        type(LOGIN_EMAIL_INPUT, email);
        type(LOGIN_PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        return new HomePage();
    }

    public AccountInformationPage startSignup(String name, String email) {
        type(SIGNUP_NAME_INPUT, name);
        type(SIGNUP_EMAIL_INPUT, email);
        click(SIGNUP_BUTTON);
        return new AccountInformationPage();
    }

    public boolean isEmailAlreadyExistsMessageVisible() {
        return isVisible(EMAIL_ALREADY_EXISTS_MESSAGE);
    }
}
