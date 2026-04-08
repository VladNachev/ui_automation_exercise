package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class ProductsPage extends BasePage {

    private static final By ALL_PRODUCTS_HEADER = By.cssSelector(".features_items .title.text-center");
    private static final By PRODUCT_CARDS = By.cssSelector(".features_items .product-image-wrapper");
    private static final By VIEW_PRODUCT_LINKS = By.cssSelector(".features_items a[href*='/product_details/']");
    private static final By CATEGORIES_SIDEBAR = By.xpath("//h2[normalize-space()='Category']");

    public ProductsPage open() {
        driver().get(FrameworkConfig.baseUrl() + "products");
        waitForDocumentReady();
        titleContains("All Products");
        return this;
    }

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

    public boolean isTitleCorrect() {
        String expectedTitle = "All Products";
        return driver().getTitle().contains(expectedTitle);
    }

    public boolean isAllProductsHeaderLoaded() {
        return isVisible(ALL_PRODUCTS_HEADER);
    }

    public boolean isCategoriesSidebarVisible() {
        return isVisible(CATEGORIES_SIDEBAR);
    }
}
