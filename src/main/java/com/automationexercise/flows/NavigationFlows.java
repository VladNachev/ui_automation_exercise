package com.automationexercise.flows;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.TCPage;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationFlows extends BaseFlow {
    private static final Logger logger = LoggerFactory.getLogger(NavigationFlows.class);

    @Step("Navigate to Test Cases page and verify it is loaded")
    public TCPage navigateToTestCasesPageAndVerify() {
        logger.info("Starting flow to navigate to Test Cases page and verify it is loaded");
        HomePage homePage = new HomePage().open();
        TCPage tcPage = homePage.clickTestCasesLink();
        ensure(tcPage.isTitleCorrect(), "Test Cases page title should be correct, indicating the page is loaded");
        return tcPage;
    }
}
