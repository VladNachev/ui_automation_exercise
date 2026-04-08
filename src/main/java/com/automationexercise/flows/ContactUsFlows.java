package com.automationexercise.flows;

import com.automationexercise.model.User;
import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.utils.TestDataFactory;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactUsFlows {

    private static final Logger logger = LoggerFactory.getLogger(ContactUsFlows.class);

    @Step("Open Contact Us page and verify it is loaded")
    public ContactUsPage openContactUsPageAndVerifyHeader() {
        logger.info("Starting flow to open Contact Us page and verify it is loaded");
        ContactUsPage contactUsPage = new ContactUsPage().open();
        ensure(contactUsPage.isContactUsHeaderLoaded(), "Contact Us header should be visible, indicating the page is loaded");
        return contactUsPage;
    }

    @Step("Filling the form and uploading a file")
    public ContactUsPage fillContactFormAndSubmit() {
        logger.info("Starting flow to fill the contact form");
        User user = TestDataFactory.createUniqueUser();
        ContactUsPage contactUsPage = new ContactUsPage();
        contactUsPage.fillContactFormAndSubmit(user.firstName(), user.email(), "Test Subject", "This is a test message.");
        return new ContactUsPage();
    }

    @Step("Wait for submit alert text and confirm")
    public ContactUsPage waitForSubmitAlertTextAndConfirm() {
        logger.info("Waiting for submit alert and confirming it");
        ContactUsPage contactUsPage = new ContactUsPage();
        return contactUsPage.waitForSubmitAlertTextAndConfirm();
    }

    private void ensure(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
