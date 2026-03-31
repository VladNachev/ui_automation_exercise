package com.automationexercise.base;

import com.automationexercise.driver.DriverFactory;
import com.automationexercise.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseUiTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            DriverManager.getDriver().quit();
        } catch (IllegalStateException ignored) {
            // The driver may not have been created if setup failed.
        } finally {
            DriverManager.unload();
        }
    }
}
