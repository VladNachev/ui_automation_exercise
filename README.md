# Automation Exercise UI Automation Framework

Java UI test automation framework for [Automation Exercise](https://automationexercise.com/) built with Maven, TestNG, Selenium WebDriver, Selenium Manager, SLF4J, Log4j2, and Allure.

## What this project includes

- Java 21
- Maven Wrapper
- TestNG
- Selenium WebDriver
- Selenium Manager
- Page Object Model
- Reusable flows layer
- SLF4J + Log4j2 logging
- Allure reporting

## Test Case Tracker

Source: [Automation Exercise Test Cases](https://automationexercise.com/test_cases), verified on April 1, 2026.

| # | Official test case | Status |
|---|---|--------|
| 1 | Register User | Done   |
| 2 | Login User with correct email and password | Done   |
| 3 | Login User with incorrect email and password | Done   |
| 4 | Register User with existing email | Done   |
| 5 | Contact Us Form | To Do  |
| 6 | Verify Test Cases Page | To Do  |
| 7 | Verify All Products and product detail page | Done   |
| 8 | Search Product | To Do  |
| 9 | Verify Subscription in home page | To Do  |
| 10 | Verify Subscription in Cart page | To Do  |
| 11 | Add Products in Cart | To Do  |
| 12 | Verify Product quantity in Cart | To Do  |
| 13 | Place Order: Register while Checkout | To Do  |
| 14 | Place Order: Register before Checkout | To Do  |
| 15 | Place Order: Login before Checkout | To Do  |
| 16 | Remove Products From Cart | To Do  |
| 17 | View Category Products | To Do  |
| 18 | View & Cart Brand Products | To Do  |
| 19 | Search Products and Verify Cart After Login | To Do  |
| 20 | Add review on product | To Do  |
| 21 | Add to cart from Recommended items | To Do  |
| 22 | Verify address details in checkout page | To Do  |
| 23 | Download Invoice after purchase order | To Do  |
| 24 | Verify Scroll Up using 'Arrow' button and Scroll Down functionality | To Do  |
| 25 | Verify Scroll Up without 'Arrow' button and Scroll Down functionality | To Do  |

## Project structure

```text
src
|- main
|  `- java/com/automationexercise
|     |- config
|     |- driver
|     |- flows
|     |- model
|     |- pages
|     `- utils
`- test
   |- java/com/automationexercise
   |  |- base
   |  |- listeners
   |  `- tests
   `- resources
```

## How to run

```powershell
.\mvnw.cmd clean test
```

```powershell
.\mvnw.cmd clean test -Dheadless=true
```

```powershell
.\mvnw.cmd allure:report
```

## Notes

- Selenium Manager resolves the browser driver automatically.
- Test lifecycle logs and page-action logs are written to the console and to `logs/framework.log` file.
