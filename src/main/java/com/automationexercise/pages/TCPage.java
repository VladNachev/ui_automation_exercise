package com.automationexercise.pages;

import org.openqa.selenium.By;

public class TCPage extends BasePage {

    private static final By TEST_CASES_HEADER = By.xpath("//h2[normalize-space(.)='Test Cases']");

    public boolean isTestCasesHeaderVisible() {
        return isVisible(TEST_CASES_HEADER);
    }

    public boolean isTitleCorrect() {
        String expectedTitle = "Test Cases";
        return driver().getTitle().contains(expectedTitle);
    }

}
