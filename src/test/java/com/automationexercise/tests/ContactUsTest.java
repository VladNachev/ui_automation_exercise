package com.automationexercise.tests;

import com.automationexercise.pages.ContactUsPage;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import com.automationexercise.base.BaseUiTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automationexercise.flows.ContactUsFlows;

@Listeners(com.automationexercise.listeners.AllureTestListener.class)
@Feature("Contact Us")
public class ContactUsTest extends BaseUiTest {
    private final ContactUsFlows contactUsFlows = new ContactUsFlows();

    @Test(description = "Test Case 5: Contact Us Form")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Contact Us form and its functionality")
    public void shouldSubmitContactFormSuccessfully() {
        ContactUsPage contactUsPage = contactUsFlows.openContactUsPageAndVerifyHeader();
        Assert.assertTrue(contactUsPage.isGetInTouchHeaderLoaded(), "Get In Touch title should be visible");

        contactUsFlows.fillContactFormAndSubmit();

        ContactUsPage resultPage = contactUsFlows.waitForSubmitAlertTextAndConfirm();
        resultPage.isSuccessMessageVisible();

    }

}
