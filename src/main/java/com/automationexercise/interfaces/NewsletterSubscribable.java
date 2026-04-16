package com.automationexercise.interfaces;

public interface NewsletterSubscribable<T> {

    T subscribeToNewsletter(String email);

    boolean isNewsletterSuccessMessageVisible();
}
