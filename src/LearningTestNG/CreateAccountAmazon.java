package LearningTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateAccountAmazon {
    WebDriver driver;
    Actions action;
    @BeforeClass
    public void openBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void openAmazonURL(){
        driver.get("https://www.amazon.de/");
        Assert.assertTrue(driver.findElement(By.id("sp-cc-accept")).isDisplayed());
    }
    @Test(dependsOnMethods = "openAmazonURL")
    public void acceptCookies(){
        action = new Actions(driver);
        driver.findElement(By.id("sp-cc-accept")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).isDisplayed());
    }
    @Test(dependsOnMethods = "acceptCookies")
    public void changeLanguage(){
        action.moveToElement(driver.findElement(By.xpath("//a[@id='icp-nav-flyout']"))).click().perform();
        driver.findElement(By.xpath("//span[contains(text(),'- Translation')]")).click();
        //Utility.captureScreenshot(driver);
        driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
        //Utility.captureScreenshot(driver);
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).isDisplayed());
    }
    @Test(dependsOnMethods = "changeLanguage",description = "Test case to verify error messages for blank fields")
    public void createAccount(){
        action.moveToElement(driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"))).click().perform();
        driver.findElement(By.xpath("//a[@id='createAccountSubmit']")).click();
        /*driver.findElement(By.id("ap_customer_name")).sendKeys("Zoe Kramer");
        driver.findElement(By.id("ap_email")).sendKeys("kramerzoe90@gmail.com");
        driver.findElement(By.id("ap_password")).sendKeys("@Zoekramer90");
        driver.findElement(By.id("ap_password_check")).sendKeys("@Zoekramer90");*/
        driver.findElement(By.id("continue")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Utility.captureScreenshot(driver);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Enter your name')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Enter your e-mail address or mobile phone number')]")).isDisplayed());
        /*
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Gib deinen Namen ein')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Gib deine E-Mail-Adresse oder Mobiltelefonnummer ein.')]")).isDisplayed());*/

    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
