package com.example;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalLoginTest {

    WebDriver driver;

    // Runs before every test
    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        // Check whether code is running on GitHub Actions
        if (System.getenv("GITHUB_ACTIONS") != null) {

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

        }

        // Launch Chrome browser
        driver = new ChromeDriver(options);

        // Maximize browser only during local execution
        if (System.getenv("GITHUB_ACTIONS") == null) {
            driver.manage().window().maximize();
        }

        // Open local login webpage
        File file = new File("webpage/login.html");

        if (!file.exists()) {
            throw new RuntimeException("login.html file not found!");
        }

        String page = file.getAbsoluteFile().toURI().toString();

        driver.get(page);
    }

    // Test Case 1: Valid Login
    @Test
    public void validLoginTest() {

        driver.findElement(By.id("username")).sendKeys("student");

        driver.findElement(By.id("password")).sendKeys("12345");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message, "Login Successful");
    }

    // Test Case 2: Invalid Login
    @Test
    public void invalidLoginTest() {

        driver.findElement(By.id("username")).sendKeys("wrong");

        driver.findElement(By.id("password")).sendKeys("wrong");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message, "Invalid Username or Password");
    }

    // Test Case 3: Check Background Color
    @Test
    public void checkBackgroundColor() {

        String bgColor = driver.findElement(By.id("loginBox"))
                .getCssValue("background-color");

        System.out.println("Background Color: " + bgColor);

        Assert.assertEquals(bgColor, "rgba(173, 216, 230, 1)");
    }

    // Runs after every test
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}