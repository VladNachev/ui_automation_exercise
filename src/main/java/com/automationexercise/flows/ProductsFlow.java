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
        ProductsPage productsPage = new ProductsPage().open();
        ensure(productsPage.isAllProductsHeaderLoaded(), "All Products header should be visible, indicating the page is loaded");
        return productsPage;
    }
}
