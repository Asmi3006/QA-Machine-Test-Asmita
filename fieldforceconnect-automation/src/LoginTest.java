package com.fieldforceconnect.tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://test.fieldforceconnect.com/auth/login");
    }

     
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"bhalsaneasmita@gmail.com", "test@3333"},
            {"8779266438", "test@3333"}
        };
    }
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
            .sendKeys(username);

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")))
            .sendKeys(password);

        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")))
            .click();

         
        wait.until(ExpectedConditions.urlContains("dashboard"));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("dashboard"),
            "Login failed - Dashboard was not opened"
        );

        System.out.println("Login successful - Dashboard opened!");
    }
}