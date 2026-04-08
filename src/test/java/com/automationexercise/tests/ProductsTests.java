package com.automationexercise.tests;

import com.automationexercise.base.BaseUiTest;
import com.automationexercise.flows.ProductsFlow;
import com.automationexercise.pages.ProductDetailsPage;
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
@Feature("Products")
public class ProductsTests extends BaseUiTest {
    public static final String searchTerm = "Man TShirt";

    @Test(description = "Test Case 8: Verify All Products and product detail page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies the All Products page is reachable through the dedicated products flow, the product list is visible, and the first product detail page exposes the required product information.")
    public void shouldOpenAllProductsAndFirstProductDetails() {
        ProductsPage productsPage = new ProductsFlow().openProductsPageAndVerifyTitle();
        Assert.assertTrue(productsPage.isAllProductsHeaderLoaded(), "All Products header should be visible");
        Assert.assertTrue(productsPage.isProductListVisible(), "The products list should be visible");

        SoftAssert softAssert = new SoftAssert();

        ProductDetailsPage productDetailsPage = productsPage.viewFirstProduct();
        softAssert.assertTrue(productDetailsPage.isLoaded(), "User should land on the product detail page");
        softAssert.assertTrue(productDetailsPage.hasVisibleProductName(), "Product name should be visible");
        softAssert.assertTrue(productDetailsPage.hasVisibleCategory(), "Category should be visible");
        softAssert.assertTrue(productDetailsPage.hasVisiblePrice(), "Price should be visible");
        softAssert.assertTrue(productDetailsPage.hasVisibleAvailability(), "Availability should be visible");
        softAssert.assertTrue(productDetailsPage.hasVisibleCondition(), "Condition should be visible");
        softAssert.assertTrue(productDetailsPage.hasVisibleBrand(), "Brand should be visible");

        softAssert.assertAll();
    }

    @Test(description = "Test Case 9: Verify search functionality in Products page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that the search functionality on the Products page works correctly by searching for a specific product and checking that the search results are displayed.")
    public void shouldSearchForProduct() {
        ProductsPage productsPage = new ProductsFlow().searchForProductAndVerify(searchTerm);
        Assert.assertTrue(productsPage.isSearchedResultItemVisible(searchTerm), "Searched Products header should be visible, indicating the search results are displayed");
    }
}
