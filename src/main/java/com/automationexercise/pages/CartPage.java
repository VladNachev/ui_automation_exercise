package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import com.automationexercise.interfaces.NewsletterSubscribable;
import org.openqa.selenium.By;

public class CartPage extends BasePage implements NewsletterSubscribable<CartPage> {
    private static final String PAGE_URL = "/view_cart";

    // Locators
    private static final By CART_PAGE_HEADER = By.xpath("//li[normalize-space()='Shopping Cart']");

    public CartPage open() {
        driver().get(FrameworkConfig.baseUrl() + PAGE_URL);
        waitForDocumentReady();
        return this;
    }

    public boolean isLoaded() {
        return isVisible(CART_PAGE_HEADER);
    }

    public CartPage subscribeToNewsletter(String email) {
        subscribeToNewsletterInFooter(email);
        return this;
    }

    public boolean isNewsletterSuccessMessageVisible() {
        return isNewsletterSuccessMessageVisibleInFooter();
    }

}
