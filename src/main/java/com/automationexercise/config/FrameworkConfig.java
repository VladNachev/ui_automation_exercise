package com.automationexercise.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public final class FrameworkConfig {

    private static final Properties PROPERTIES = loadProperties();

    private FrameworkConfig() {
    }

    public static String baseUrl() {
        return get("base.url");
    }

    public static String browser() {
        return get("browser");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static Duration explicitWait() {
        return Duration.ofSeconds(Long.parseLong(get("explicit.wait.seconds")));
    }

    public static Duration pageLoadTimeout() {
        return Duration.ofSeconds(Long.parseLong(get("page.load.timeout.seconds")));
    }

    private static String get(String key) {
        return System.getProperty(key, Objects.requireNonNull(PROPERTIES.getProperty(key), () -> "Missing property: " + key));
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = FrameworkConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties was not found on the classpath");
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load framework configuration", exception);
        }
    }
}
