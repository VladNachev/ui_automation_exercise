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
}
