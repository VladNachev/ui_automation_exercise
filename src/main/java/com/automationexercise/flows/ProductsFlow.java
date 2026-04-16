package com.automationexercise.flows;

import com.automationexercise.pages.ProductsPage;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductsFlow extends BaseFlow {
    private static final Logger logger = LoggerFactory.getLogger(ProductsFlow.class);

    @Step("Open Products page and verify it is loaded")
    public ProductsPage openProductsPageAndVerifyTitle() {
        logger.info("Starting flow to open Products page and verify it is loaded");
        return openVerifiedProductsPage();
    }

    @Step("Perform product search for {searchTerm} and verify Searched Products header is visible")
    public ProductsPage searchForProductAndVerify(String searchTerm) {
        return searchForProductAndVerify(openVerifiedProductsPage(), searchTerm);
    }

    @Step("Perform product search for {searchTerm} on the current Products page and verify Searched Products header is visible")
    public ProductsPage searchForProductAndVerify(ProductsPage productsPage, String searchTerm) {
        logger.info("Searching for product '{}' on the current Products page", searchTerm);
        productsPage.searchForProduct(searchTerm);
        ensure(productsPage.isSearchedProductsHeaderVisible(), "Searched Products header should be visible, indicating the search results are displayed");
        return productsPage;
    }

    @Step("Add product in position {productPosition} to cart and continue shopping")
    public ProductsPage addProductToCartAndContinue(int productPosition) {
        return addProductToCartAndContinue(openVerifiedProductsPage(), productPosition);
    }

    @Step("Add product in position {productPosition} to cart on the current Products page and continue shopping")
    public ProductsPage addProductToCartAndContinue(ProductsPage productsPage, int productPosition) {
        logger.info("Adding product in position {} to cart and continuing shopping on the current Products page", productPosition);
        ensure(productsPage.isAllProductsHeaderLoaded(), "All Products header should be visible before adding a product to cart");
        return productsPage.addProductToCart(productPosition).clickContinueShopping();
    }

    private ProductsPage openVerifiedProductsPage() {
        ProductsPage productsPage = new ProductsPage().open();
        ensure(productsPage.isAllProductsHeaderLoaded(), "All Products header should be visible, indicating the page is loaded");
        return productsPage;
    }
}
