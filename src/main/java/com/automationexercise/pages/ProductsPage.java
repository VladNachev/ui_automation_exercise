package com.automationexercise.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    private static final By ALL_PRODUCTS_TITLE = By.cssSelector(".features_items .title.text-center");
    private static final By PRODUCT_CARDS = By.cssSelector(".features_items .product-image-wrapper");
    private static final By VIEW_PRODUCT_LINKS = By.cssSelector(".features_items a[href*='/product_details/']");

    public boolean isLoaded() {
        return driver().getCurrentUrl().contains("/products");
    }

    public boolean isProductListVisible() {
        List<WebElement> productCards = driver().findElements(PRODUCT_CARDS);
        return !productCards.isEmpty() && productCards.get(0).isDisplayed();
    }

    public ProductDetailsPage viewFirstProduct() {
        click(driver().findElements(VIEW_PRODUCT_LINKS).get(0));
        return new ProductDetailsPage();
    }
}
