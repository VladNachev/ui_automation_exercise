package com.automationexercise.pages;

import org.openqa.selenium.By;

public class AccountDeletedPage extends BasePage {

    private static final By ACCOUNT_DELETED_HEADER = By.xpath("//b[contains(.,'Account Deleted!')]");
    private static final By CONTINUE_BUTTON = By.cssSelector("a[data-qa='continue-button']");

    public boolean isVisible() {
        return isVisible(ACCOUNT_DELETED_HEADER);
    }

    public HomePage clickContinue() {
        click(CONTINUE_BUTTON);
        return new HomePage();
    }
}
