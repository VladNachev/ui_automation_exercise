package com.automationexercise.listeners;

import com.automationexercise.utils.ScreenshotUtils;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            attachScreenshot();
        } catch (Exception ignored) {
            // Best-effort attachment when the driver is available.
        }
    }

    @Attachment(value = "Failure screenshot", type = "image/png")
    private byte[] attachScreenshot() {
        return ScreenshotUtils.takeScreenshot();
    }
}
