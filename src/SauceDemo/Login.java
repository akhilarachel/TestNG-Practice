package SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {
    WebDriver driver;

    @BeforeClass
    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test(priority = 1)
    public void launchURL(){
        driver.get("https://www.saucedemo.com/");
    }
    @Test(dependsOnMethods = "launchURL")
    public void login(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Products')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "login")
    public void sortProductsLH(){
        Select dropdownValues = new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
        dropdownValues.selectByVisibleText("Price (low to high)");
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Price (low to high)')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "sortProductsLH")
    public void buyProduct(){
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Your Cart')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "buyProduct")
    public void checkoutProcess(){
        driver.findElement(By.id("checkout")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Checkout: Your Information')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "checkoutProcess")
    public void completeCheckout(){
        driver.findElement(By.id("first-name")).sendKeys("Aaron");
        driver.findElement(By.id("last-name")).sendKeys("Wagner");
        driver.findElement(By.id("postal-code")).sendKeys("123456");
        driver.findElement(By.id("continue")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Checkout: Overview')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "completeCheckout")
    public void completePurchase(){
        driver.findElement(By.id("finish")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Checkout: Complete!')]")).isDisplayed());
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
