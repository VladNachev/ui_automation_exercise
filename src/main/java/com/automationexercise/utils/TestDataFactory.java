package com.automationexercise.utils;

import com.automationexercise.model.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestDataFactory {

    private static final DateTimeFormatter EMAIL_SUFFIX_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private TestDataFactory() {
    }

    public static User createUniqueUser() {
        String suffix = LocalDateTime.now().format(EMAIL_SUFFIX_FORMATTER);
        return new User(
                "Mr",
                "Nikolay",
                "Automation" + suffix.substring(suffix.length() - 4),
                "nikolay.automation." + suffix + "@mailinator.com",
                "SecurePassword123!",
                "10",
                "May",
                "1995",
                "QA Labs",
                "42 Testing Street",
                "Suite 7",
                "Canada",
                "Ontario",
                "Toronto",
                "10001",
                "0888123456"
        );
    }

    public static User createNonExistingUser() {
        String suffix = LocalDateTime.now().format(EMAIL_SUFFIX_FORMATTER);
        return new User(
                "Ms",
                "Jane",
                "Doe" + suffix.substring(suffix.length() - 4),
                "jane.doe." + suffix + "@mailinator.com",
                "AnotherSecurePassword123!",
                "15",
                "June",
                "1990",
                "Example Corp",
                "123 Main Street",
                "Apt 4B",
                "United States",
                "California",
                "Los Angeles",
                "90001",
                "0888765432"
        );
    }
}
