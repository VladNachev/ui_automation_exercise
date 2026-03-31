package com.automationexercise.pages;

import org.openqa.selenium.By;

public class AccountCreatedPage extends BasePage {

    private static final By ACCOUNT_CREATED_HEADER = By.xpath("//b[contains(.,'Account Created!')]");
    private static final By CONTINUE_BUTTON = By.cssSelector("a[data-qa='continue-button']");

    public boolean isVisible() {
        return isVisible(ACCOUNT_CREATED_HEADER);
    }

    public HomePage clickContinue() {
        click(CONTINUE_BUTTON);
        return new HomePage();
    }
}
