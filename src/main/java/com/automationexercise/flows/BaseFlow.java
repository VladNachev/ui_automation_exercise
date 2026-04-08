package com.automationexercise.flows;

public abstract class BaseFlow {

    protected void ensure(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
