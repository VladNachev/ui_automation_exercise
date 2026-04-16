package com.automationexercise.tests;

import com.automationexercise.base.BaseUiTest;
import com.automationexercise.flows.SubscribingFlow;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.automationexercise.listeners.AllureTestListener.class)
@Feature("Subscription feature")

public class SubscriptionTests extends BaseUiTest {

    private final SubscribingFlow subscribingFlow = new SubscribingFlow();
    private final String email = "testuser" + System.currentTimeMillis() + "@example.com";

    @Test(description = "Test Case 9: Verify Subscription in home page")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the user can subscribe to the newsletter from the home page and receives a success message.")
    public void shouldSubscribeToNewsletterFromHomePage() {
        HomePage homePage = subscribingFlow.subscribeForNewsletterFromHomePage(email);

        Assert.assertTrue(homePage.isNewsletterSuccessMessageVisible(), "Success message should be visible after subscribing to the newsletter");
    }

    @Test(description = "Test Case 10: Verify Subscription in Cart page")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the user can subscribe to the newsletter from the Cart page and receives a success message.")
    public void shouldSubscribeToNewsletterFromCartPage() {
        CartPage cartPage = subscribingFlow.subscribeForNewsletterFromCartPage(email);

        Assert.assertTrue(cartPage.isNewsletterSuccessMessageVisible(), "Success message should be visible after subscribing to the newsletter");
    }

}
