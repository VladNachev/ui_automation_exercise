package com.automationexercise.tests;

import com.automationexercise.base.BaseUiTest;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductDetailsPage;
import com.automationexercise.pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.automationexercise.listeners.AllureTestListener.class)
@Feature("Products")
public class ProductsTests extends BaseUiTest {

    @Test(description = "Test Case 7: Verify All Products and product detail page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies the All Products page is reachable, the product list is visible, and the first product detail page exposes the required product information.")
    public void shouldOpenAllProductsAndFirstProductDetails() {
        HomePage homePage = new HomePage().open();
        Assert.assertTrue(homePage.isLoaded(), "Home page should be visible");

        ProductsPage productsPage = homePage.clickProducts();
        Assert.assertTrue(productsPage.isLoaded(), "All Products page should be visible");
        Assert.assertTrue(productsPage.isProductListVisible(), "The products list should be visible");

        ProductDetailsPage productDetailsPage = productsPage.viewFirstProduct();
        Assert.assertTrue(productDetailsPage.isLoaded(), "User should land on the product detail page");
        Assert.assertTrue(productDetailsPage.hasVisibleProductName(), "Product name should be visible");
        Assert.assertTrue(productDetailsPage.hasVisibleCategory(), "Category should be visible");
        Assert.assertTrue(productDetailsPage.hasVisiblePrice(), "Price should be visible");
        Assert.assertTrue(productDetailsPage.hasVisibleAvailability(), "Availability should be visible");
        Assert.assertTrue(productDetailsPage.hasVisibleCondition(), "Condition should be visible");
        Assert.assertTrue(productDetailsPage.hasVisibleBrand(), "Brand should be visible");
    }
}
