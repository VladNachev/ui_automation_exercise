package com.automationexercise.listeners;

import com.automationexercise.utils.ScreenshotUtils;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllureTestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(AllureTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("STARTING TEST: {}", testName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("PASSED TEST: {}", testName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("FAILED TEST: {}", testName(result), result.getThrowable());
        try {
            attachScreenshot();
        } catch (Exception ignored) {
            // Best-effort attachment when the driver is available.
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("SKIPPED TEST: {}", testName(result));
    }

    @Attachment(value = "Failure screenshot", type = "image/png")
    private byte[] attachScreenshot() {
        return ScreenshotUtils.takeScreenshot();
    }

    private String testName(ITestResult result) {
        String description = result.getMethod().getDescription();
        return description == null || description.isBlank()
                ? result.getMethod().getMethodName()
                : description;
    }
}
