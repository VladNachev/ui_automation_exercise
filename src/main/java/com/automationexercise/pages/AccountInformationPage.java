package com.automationexercise.pages;

import com.automationexercise.model.User;
import org.openqa.selenium.By;

public class AccountInformationPage extends BasePage {

    private static final By ENTER_ACCOUNT_INFORMATION_HEADER = By.xpath("//b[contains(.,'Enter Account Information')]");
    private static final By TITLE_MR_RADIO = By.id("id_gender1");
    private static final By TITLE_MRS_RADIO = By.id("id_gender2");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By DAYS_SELECT = By.id("days");
    private static final By MONTHS_SELECT = By.id("months");
    private static final By YEARS_SELECT = By.id("years");
    private static final By NEWSLETTER_CHECKBOX = By.id("newsletter");
    private static final By OFFERS_CHECKBOX = By.id("optin");
    private static final By FIRST_NAME_INPUT = By.id("first_name");
    private static final By LAST_NAME_INPUT = By.id("last_name");
    private static final By COMPANY_INPUT = By.id("company");
    private static final By ADDRESS_ONE_INPUT = By.id("address1");
    private static final By ADDRESS_TWO_INPUT = By.id("address2");
    private static final By COUNTRY_SELECT = By.id("country");
    private static final By STATE_INPUT = By.id("state");
    private static final By CITY_INPUT = By.id("city");
    private static final By ZIP_CODE_INPUT = By.id("zipcode");
    private static final By MOBILE_NUMBER_INPUT = By.id("mobile_number");
    private static final By CREATE_ACCOUNT_BUTTON = By.cssSelector("button[data-qa='create-account']");

    public boolean isLoaded() {
        return isVisible(ENTER_ACCOUNT_INFORMATION_HEADER);
    }

    public AccountCreatedPage register(User user) {
        if ("Mrs".equalsIgnoreCase(user.title())) {
            click(TITLE_MRS_RADIO);
        } else {
            click(TITLE_MR_RADIO);
        }

        type(PASSWORD_INPUT, user.password());
        selectByVisibleText(DAYS_SELECT, user.birthDay());
        selectByVisibleText(MONTHS_SELECT, user.birthMonth());
        selectByVisibleText(YEARS_SELECT, user.birthYear());
        click(NEWSLETTER_CHECKBOX);
        click(OFFERS_CHECKBOX);
        type(FIRST_NAME_INPUT, user.firstName());
        type(LAST_NAME_INPUT, user.lastName());
        type(COMPANY_INPUT, user.company());
        type(ADDRESS_ONE_INPUT, user.addressLine1());
        type(ADDRESS_TWO_INPUT, user.addressLine2());
        selectByVisibleText(COUNTRY_SELECT, user.country());
        type(STATE_INPUT, user.state());
        type(CITY_INPUT, user.city());
        type(ZIP_CODE_INPUT, user.zipCode());
        type(MOBILE_NUMBER_INPUT, user.mobileNumber());
        click(CREATE_ACCOUNT_BUTTON);
        return new AccountCreatedPage();
    }
}
