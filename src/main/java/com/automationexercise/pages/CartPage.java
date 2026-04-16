package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import com.automationexercise.interfaces.NewsletterSubscribable;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage implements NewsletterSubscribable<CartPage> {
    private static final String PAGE_URL = "/view_cart";

    // Locators
    private static final By CART_PAGE_HEADER = By.xpath("//li[normalize-space()='Shopping Cart']");
    private static final By CART_PRODUCT_ROWS = By.cssSelector("tbody tr[id^='product-']");

    public CartPage open() {
        driver().get(FrameworkConfig.baseUrl() + PAGE_URL);
        waitForDocumentReady();
        return this;
    }

    public boolean isLoaded() {
        return isVisible(CART_PAGE_HEADER);
    }

    public int getProductsCountInCart() {
        return driver().findElements(CART_PRODUCT_ROWS).size();
    }

    public List<String> getProductIdsInCart() {
        return driver().findElements(CART_PRODUCT_ROWS).stream()
                .map(row -> row.getAttribute("id"))
                .map(id -> id.replace("product-", ""))
                .collect(Collectors.toList());
    }

    public CartPage subscribeToNewsletter(String email) {
        subscribeToNewsletterInFooter(email);
        return this;
    }

    public boolean isNewsletterSuccessMessageVisible() {
        return isNewsletterSuccessMessageVisibleInFooter();
    }

}
