package com.automationexercise.pages;

import com.automationexercise.config.FrameworkConfig;
import com.automationexercise.driver.DriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final WebDriverWait wait;

    protected BasePage() {
        this.wait = new WebDriverWait(driver(), FrameworkConfig.explicitWait());
    }

    protected WebDriver driver() {
        return DriverManager.getDriver();
    }

    protected void click(By locator) {
        WebElement element = waitUntilClickable(locator);
        scrollIntoView(element);
        try {
            element.click();
        } catch (Exception exception) {
            logger.debug("Standard click failed for {}. Falling back to JavaScript click.", locator, exception);
            ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = waitUntilClickable(locator);
        scrollIntoView(element);
        try {
            element.click();
            element.clear();
            element.sendKeys(text);
        } catch (ElementNotInteractableException exception) {
            logger.debug("Standard typing failed for {}. Falling back to JavaScript value assignment.", locator, exception);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver();
            javascriptExecutor.executeScript("arguments[0].value='';", element);
            javascriptExecutor.executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', {bubbles: true})); arguments[0].dispatchEvent(new Event('change', {bubbles: true}));",
                    element,
                    text
            );
        }
    }

    protected void selectByVisibleText(By locator, String visibleText) {
        new Select(waitUntilVisible(locator)).selectByVisibleText(visibleText);
    }

    protected String textOf(By locator) {
        return waitUntilVisible(locator).getText().trim();
    }

    protected boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    protected void waitForDocumentReady() {
        new WebDriverWait(driver(), Duration.ofSeconds(20)).until(webDriver ->
                "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
    }

    protected WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
