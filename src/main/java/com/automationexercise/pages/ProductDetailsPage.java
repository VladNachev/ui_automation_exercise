package com.automationexercise.pages;

import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    private static final By PRODUCT_INFORMATION_PANEL = By.cssSelector(".product-information");
    private static final By PRODUCT_NAME = By.cssSelector(".product-information h2");
    private static final By PRODUCT_CATEGORY = By.xpath("//div[@class='product-information']/p[contains(.,'Category:')]");
    private static final By PRODUCT_PRICE = By.cssSelector(".product-information span span");
    private static final By PRODUCT_AVAILABILITY = By.xpath("//div[@class='product-information']//p[contains(.,'Availability:')]");
    private static final By PRODUCT_CONDITION = By.xpath("//div[@class='product-information']//p[contains(.,'Condition:')]");
    private static final By PRODUCT_BRAND = By.xpath("//div[@class='product-information']//p[contains(.,'Brand:')]");

    public boolean isLoaded() {
        return isVisible(PRODUCT_INFORMATION_PANEL);
    }

    public boolean hasVisibleProductName() {
        return isVisible(PRODUCT_NAME) && !textOf(PRODUCT_NAME).isBlank();
    }

    public boolean hasVisibleCategory() {
        return pageText().contains("Category:");
    }

    public boolean hasVisiblePrice() {
        return pageText().contains("Rs.");
    }

    public boolean hasVisibleAvailability() {
        return pageText().contains("Availability:");
    }

    public boolean hasVisibleCondition() {
        return pageText().contains("Condition:");
    }

    public boolean hasVisibleBrand() {
        return pageText().contains("Brand:");
    }
}
