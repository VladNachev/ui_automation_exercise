package com.automationexercise.driver;

import com.automationexercise.config.FrameworkConfig;
import java.time.Duration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        WebDriver driver = switch (FrameworkConfig.browser().toLowerCase()) {
            case "firefox" -> buildFirefoxDriver();
            case "edge" -> buildEdgeDriver();
            case "chrome" -> buildChromeDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + FrameworkConfig.browser());
        };

        driver.manage().timeouts().pageLoadTimeout(FrameworkConfig.pageLoadTimeout());
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver buildChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (FrameworkConfig.headless()) {
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver buildFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (FrameworkConfig.headless()) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver buildEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (FrameworkConfig.headless()) {
            options.addArguments("--headless=new");
        }
        return new EdgeDriver(options);
    }
}
