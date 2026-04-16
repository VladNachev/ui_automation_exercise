package com.automationexercise.tests;

import com.automationexercise.base.BaseUiTest;
import com.automationexercise.flows.ProductsFlow;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(com.automationexercise.listeners.AllureTestListener.class)
@Feature("Cart functionality tests")

public class CartTests extends BaseUiTest {
    private final ProductsFlow productsFlow = new ProductsFlow();

    @Test(description = "Test Case 12: Add Products in Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can add the first product to the cart, continue shopping, then add the second product and continue shopping.")
    public void shouldAddProductsToCart() {
        ProductsPage productsPage = productsFlow.openProductsPageAndVerifyTitle();
        Assert.assertTrue(productsPage.isAllProductsHeaderLoaded(), "All Products page should be visible before adding products to the cart");

        productsFlow.addProductToCartAndContinue(productsPage, 1);
        productsFlow.addProductToCartAndContinue(productsPage, 2);

        CartPage cartPage = productsPage.clickCartLink();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(cartPage.isLoaded(), "Cart page should be visible after clicking the cart link");
        softAssert.assertEquals(cartPage.getProductsCountInCart(), 2, "Two products should be present in the cart");
        softAssert.assertTrue(cartPage.getProductIdsInCart().contains("1"), "Product 1 should be present in the cart");
        softAssert.assertTrue(cartPage.getProductIdsInCart().contains("2"), "Product 2 should be present in the cart");
        softAssert.assertAll();
    }
}
