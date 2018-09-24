package com.hellofresh.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.hellofresh.selenium.base.BaseSeleniumWebPageAction;
import com.hellofresh.testng.listners.CustomListener;



@Listeners(CustomListener.class)

public class LogInPageWidget extends BaseSeleniumWebPageAction{
	
	
	private By login=By.className("login");
	private By email=By.id("email");
	private By passwd=By.id("passwd");
    private By SubmitLogin=By.id("SubmitLogin");
	protected  WebDriverWait _wait;
	protected WebDriver driver;
	String existingUserEmail = "hf_challenge_123456@hf12345.com";
    String existingUserPassword = "12345678";

    

	public LogInPageWidget(final WebDriver paramdriver, final WebDriverWait wait) {
		super(paramdriver, wait);
		driver = paramdriver;
		_wait = wait;
	}
	
	
	
	
	
	
	public void doLogin() throws InterruptedException {
		
		String fullName = "Joe Black";
        _wait.until(ExpectedConditions.visibilityOfElementLocated(login));
        click(login);
        
        WebElement elementEmail=driver.findElement(email);
        enterValueToInputField("email", elementEmail,existingUserEmail, false);
        
        WebElement elementPassword=driver.findElement(passwd);
        enterValueToInputField("passwd", elementPassword,existingUserPassword, false);
        
        click(SubmitLogin);
        
        
     
        WebElement heading = _wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        Assert.assertEquals("MY ACCOUNT", heading.getText());
        Assert. assertEquals(fullName, driver.findElement(By.className("account")).getText());
        Assert.  assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
        Assert. assertTrue(driver.findElement(By.className("logout")).isDisplayed());
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));

	}
	
	
	
  
}












