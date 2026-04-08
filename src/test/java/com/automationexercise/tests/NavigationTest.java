package com.automationexercise.tests;

import com.automationexercise.base.BaseUiTest;
import com.automationexercise.flows.NavigationFlows;
import com.automationexercise.pages.TCPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.automationexercise.listeners.AllureTestListener.class)
@Feature("Web Navigation")
public class NavigationTest extends BaseUiTest {

    @Test(description = "Test Case 6: Verify navigation to Test Cases page")
    @Severity(SeverityLevel.MINOR)
    public void shouldNavigateToTestCasesPage() {
        TCPage tcPage = new NavigationFlows().navigateToTestCasesPageAndVerify(); // inner assertion in the flow will ensure the page is loaded
        Assert.assertTrue(tcPage.isTestCasesHeaderVisible(), "User should be able to see the Test Cases page header");
    }
}
