package com.automationexercise.flows;

import com.automationexercise.interfaces.NewsletterSubscribable;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.HomePage;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscribingFlow extends BaseFlow {

    private static final Logger logger = LoggerFactory.getLogger(SubscribingFlow.class);

    @Step("Subscribe to newsletter from Home Page with email: {email}")
    public HomePage subscribeForNewsletterFromHomePage(String email) {
        HomePage homePage = new HomePage().open();
        return subscribeToNewsletter("Home Page", homePage, homePage.isLoaded(), "Home page should be visible", email);
    }

    @Step("Subscribe to newsletter from Cart Page with email: {email}")
    public CartPage subscribeForNewsletterFromCartPage(String email) {
        CartPage cartPage = new CartPage().open();
        return subscribeToNewsletter("Cart Page", cartPage, cartPage.isLoaded(), "Cart page should be visible", email);
    }

    private <T extends NewsletterSubscribable<T>> T subscribeToNewsletter(
            String pageName,
            T page,
            boolean isLoaded,
            String notLoadedMessage,
            String email
    ) {
        logger.info("Starting flow to subscribe to newsletter from {} with email: {}", pageName, email);
        ensure(isLoaded, notLoadedMessage);
        logger.info("Typing email and submitting subscription form on {}", pageName);
        return page.subscribeToNewsletter(email);
    }
}
