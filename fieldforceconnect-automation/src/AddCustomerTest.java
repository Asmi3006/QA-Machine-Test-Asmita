 package com.fieldforceconnect.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class AddCustomerTest {

    WebDriver driver;
    WebDriverWait wait;
 
    String username = System.getenv("bhalsaneasmita@gmail.com");
    String password = System.getenv("test@3333");

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

         
        driver.get("https://test.fieldforceconnect.com/auth/login");

       

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("username")))
                .sendKeys("bhalsaneasmita@gmail.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("password")))
                .sendKeys("test@3333");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")))
                .click();

         
        wait.until(ExpectedConditions.urlContains("dashboard"));

        System.out.println("Login successful - Dashboard opened");
    }

 
  

    @DataProvider(name = "customerData")
    public Object[][] customerData() {

        return new Object[][] {

            {
                "Test Customer 01",
                "Asmita",
                "9876543210",
                "testcustomer01@gmail.com"
            },

            {
                "Test Customer 02",
                "QA Team",
                "9876543211",
                "testcustomer02@gmail.com"
            }
        };
    }

    

    @Test(dataProvider = "customerData")
    public void addCustomerTest(
            String customerName,
            String contactPerson,
            String mobileNo,
            String email) {

      
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[normalize-space()='My Customers']")))
                .click();

      
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[normalize-space()='My Customer']")))
                .click();

         
        wait.until(ExpectedConditions.urlContains("customers"));

        
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(normalize-space(.),'Manage')]")))
                .click();

        
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@role='menuitem' and @title='Create New Customer']")))
                .click();

     
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("LeadName")))
                .sendKeys(customerName);

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("PersonName")))
                .sendKeys(contactPerson);

       
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("MobileNo")))
                .sendKeys(mobileNo);

 
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("Email")))
                .sendKeys(email);

      

        Assert.assertEquals(
                driver.findElement(By.name("LeadName"))
                        .getAttribute("value"),
                customerName,
                "Customer Name was not entered correctly");

        Assert.assertEquals(
                driver.findElement(By.name("PersonName"))
                        .getAttribute("value"),
                contactPerson,
                "Contact Person was not entered correctly");

        Assert.assertEquals(
                driver.findElement(By.name("MobileNo"))
                        .getAttribute("value"),
                mobileNo,
                "Mobile Number was not entered correctly");

        Assert.assertEquals(
                driver.findElement(By.name("Email"))
                        .getAttribute("value"),
                email,
                "Email was not entered correctly");

        System.out.println("Customer form data entered successfully");

        
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[normalize-space()='Save']]")))
                .click();

        System.out.println("Save button clicked for: " + customerName);

         
        wait.until(ExpectedConditions.or(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"), customerName),
                ExpectedConditions.urlContains("customers")
        ));
 
        Assert.assertTrue(
                driver.getPageSource().contains(customerName),
                "Customer was not added successfully: " + customerName);

        System.out.println(
                "Customer added successfully: " + customerName);
    }

    

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}