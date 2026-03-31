package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final By HOME_LOGO = By.cssSelector("img[alt='Website for automation practice']");
    private static final By SIGNUP_LOGIN_LINK = By.cssSelector("a[href='/login']");
    private static final By LOGGED_IN_AS_LABEL = By.xpath("//a[contains(.,'Logged in as')]");
    private static final By DELETE_ACCOUNT_LINK = By.cssSelector("a[href='/delete_account']");
    private static final By LOGOUT_LINK = By.cssSelector("a[href='/logout']");

    public HomePage open() {
        driver().get(FrameworkConfig.baseUrl());
        waitForDocumentReady();
        waitUntilVisible(HOME_LOGO);
        return this;
    }

    public boolean isLoaded() {
        return isVisible(HOME_LOGO);
    }

    public LoginPage clickSignupLogin() {
        click(SIGNUP_LOGIN_LINK);
        return new LoginPage();
    }

    public boolean isLoggedInAsVisible(String expectedName) {
        return textOf(LOGGED_IN_AS_LABEL).contains(expectedName);
    }

    public LoginPage clickLogout() {
        click(LOGOUT_LINK);
        return new LoginPage();
    }

    public AccountDeletedPage clickDeleteAccount() {
        click(DELETE_ACCOUNT_LINK);
        return new AccountDeletedPage();
    }
}
