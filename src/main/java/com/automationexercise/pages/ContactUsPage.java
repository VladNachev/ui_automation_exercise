package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class ContactUsPage extends BasePage {
    private static final By CONTACT_US_HEADER = By.xpath("//h2[normalize-space(.)='Contact Us']");
    private static final By GET_IN_TOUCH_HEADER = By.cssSelector("h2.title.text-center");
    private static final By INPUT_EMAIL = By.cssSelector("input[data-qa='email']");
    private static final By INPUT_NAME = By.cssSelector("input[data-qa='name']");
    private static final By INPUT_SUBJECT = By.cssSelector("input[data-qa='subject']");
    private static final By INPUT_MESSAGE = By.cssSelector("textarea[data-qa='message']");
    private static final By SUBMIT_BUTTON = By.cssSelector("input[data-qa='submit-button']");
    private static final By SUCCESS_MESSAGE = By.xpath("//div[normalize-space()='Success! Your details have been submitted successfully.']");

    public ContactUsPage open() {
        driver().get(FrameworkConfig.baseUrl() + "contact_us");
        waitForDocumentReady();
        titleContains("Contact Us");
        return this;
    }

    public ContactUsPage fillContactFormAndSubmit(String name, String email, String subject, String message) {
        type(INPUT_NAME, name);
        type(INPUT_EMAIL, email);
        type(INPUT_SUBJECT, subject);
        type(INPUT_MESSAGE, message);
        click(SUBMIT_BUTTON);
        return new ContactUsPage();
    }

    public ContactUsPage waitForSubmitAlertTextAndConfirm() {
        Alert alert = new WebDriverWait(driver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

        if (!alert.getText().contains("Press OK to proceed!")) {
            throw new IllegalStateException("Unexpected alert text: " + alert.getText());
        }

        alert.accept();
        return new ContactUsPage();
    }

    public boolean isContactUsHeaderLoaded() {
        return isVisible(CONTACT_US_HEADER);
    }
    public boolean isGetInTouchHeaderLoaded() {
        return isVisible(GET_IN_TOUCH_HEADER);
    }
    public boolean isSuccessMessageVisible() {
        return isVisible(SUCCESS_MESSAGE);
    }
}
