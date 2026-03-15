package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        // Firefox options (for Linux Snap Firefox)
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox");

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1920,1080));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // -------- SauceDemo Login --------
        driver.get("https://www.saucedemo.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                .sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.urlContains("inventory"));
        System.out.println("SauceDemo login successful");

        // -------- Automation Exercise --------
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://automationexercise.com/products");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")))
                .sendKeys("Men Tshirt");

        driver.findElement(By.id("submit_search")).click();

        // Add product to cart
        WebElement addCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-product-id='2']"))
        );
        addCart.click();

        // View cart
        WebElement viewCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']"))
        );
        viewCart.click();

        System.out.println("Automation Exercise product added to cart");

        // -------- Practice Test Automation Login --------
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://practicetestautomation.com/practice-test-login/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("student");

        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        wait.until(ExpectedConditions.urlContains("logged-in-successfully"));
        System.out.println("Practice Test Automation login successful");

        // Close browser after 5 sec
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
