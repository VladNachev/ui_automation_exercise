package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ProductsPage extends BasePage {
    private static final By ALL_PRODUCTS_HEADER = By.cssSelector(".features_items .title.text-center");
    private static final By PRODUCT_CARDS = By.cssSelector(".features_items .product-image-wrapper");
    private static final By VIEW_PRODUCT_LINKS = By.cssSelector(".features_items a[href*='/product_details/']");
    private static final By PRODUCT_ADD_TO_CART_BUTTONS = By.cssSelector(".features_items .productinfo a.add-to-cart");
    private static final By CATEGORIES_SIDEBAR = By.xpath("//h2[normalize-space()='Category']");
    private static final By SEARCH_PRODUCTS_INPUT = By.id("search_product");
    private static final By SEARCH_PRODUCTS_BUTTON = By.id("submit_search");
    private static final By SEARCHED_PRODUCTS_HEADER = By.xpath("//h2[normalize-space()='Searched Products']");
    private static final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("button.close-modal[data-dismiss='modal']");
    private static final By CART_LINK = By.cssSelector("a[href='/view_cart']");
    private static final String SEARCHED_RESULT_ITEM_XPATH = "//p[normalize-space()='%s']";


    public ProductsPage open() {
        driver().get(FrameworkConfig.baseUrl() + "products");
        waitForDocumentReady();
        waitForTitleContains("All Products");
        return this;
    }

    public CartPage clickCartLink() {
        jsScrollToPageTop();
        click(CART_LINK);
        if (!waitForUrlContains("/view_cart")) {
            logger.debug("Cart link did not navigate as expected. Falling back to direct test cases URL.");
            driver().navigate().to(FrameworkConfig.baseUrl() + "view_cart");
            waitForDocumentReady();
        }
        return new CartPage();
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

    public ProductsPage addProductToCart(int productPosition) {
        if (productPosition < 1) {
            throw new IllegalArgumentException("Product position must be 1 or greater");
        }

        List<WebElement> addToCartButtons = driver().findElements(PRODUCT_ADD_TO_CART_BUTTONS);
        if (productPosition > addToCartButtons.size()) {
            throw new IllegalArgumentException(
                    "Requested product position " + productPosition + " but only " + addToCartButtons.size() + " products are available"
            );
        }

        click(addToCartButtons.get(productPosition - 1));
        return this;
    }

    public ProductsPage clickContinueShopping() {
        click(CONTINUE_SHOPPING_BUTTON);
        return this;
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
