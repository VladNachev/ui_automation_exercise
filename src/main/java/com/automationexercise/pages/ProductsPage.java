package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ProductsPage extends BasePage {
    private static final By ALL_PRODUCTS_HEADER = By.cssSelector(".features_items .title.text-center");
    private static final By PRODUCT_CARDS = By.cssSelector(".features_items .product-image-wrapper");
    private static final By VIEW_PRODUCT_LINKS = By.cssSelector(".features_items a[href*='/product_details/']");
    private static final By CATEGORIES_SIDEBAR = By.xpath("//h2[normalize-space()='Category']");
    private static final By SEARCH_PRODUCTS_INPUT = By.id("search_product");
    private static final By SEARCH_PRODUCTS_BUTTON = By.id("submit_search");
    private static final By SEARCHED_PRODUCTS_HEADER = By.xpath("//h2[normalize-space()='Searched Products']");
    private static final String SEARCHED_RESULT_ITEM_XPATH = "//p[normalize-space()='%s']";

    public ProductsPage open() {
        driver().get(FrameworkConfig.baseUrl() + "products");
        waitForDocumentReady();
        waitForTitleContains("All Products");
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

    public ProductsPage searchForProduct(String searchTerm) {
        type(SEARCH_PRODUCTS_INPUT, searchTerm);
        click(SEARCH_PRODUCTS_BUTTON);
        waitForDocumentReady();
        return this;
    }

    public boolean isSearchedProductsHeaderVisible() {
        return isVisible(SEARCHED_PRODUCTS_HEADER);
    }

    public boolean isSearchedResultItemVisible(String expectedProductName) {
        By searchedResultItem = By.xpath(String.format(SEARCHED_RESULT_ITEM_XPATH, expectedProductName));
        return isVisible(searchedResultItem);
    }
}
